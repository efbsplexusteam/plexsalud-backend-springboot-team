package com.plexsalud.plexsalud.auth.configs;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.plexsalud.plexsalud.doctor.repositories.DoctorRepository;
import com.plexsalud.plexsalud.patient.repositories.PatientRepository;

@Configuration
public class ApplicationConfiguration {
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    public ApplicationConfiguration(DoctorRepository doctorRepository, PatientRepository patientRepository) {
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Primary
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    UserDetailsService userDetailsService() {
        return username -> patientRepository.findByEmail(username).map(p -> (UserDetails) p)
                .orElseGet(() -> doctorRepository
                        .findByEmail(username).map(d -> (UserDetails) d)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found")));
    }

    @Bean
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Bean
    public DaoAuthenticationProvider patientAuthProvider(
            @Qualifier("patientUserDetailsService") UserDetailsService patientUserDetailsService,
            PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(patientUserDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }

    @Bean
    public DaoAuthenticationProvider doctorAuthProvider(
            @Qualifier("doctorUserDetailsService") UserDetailsService doctorUserDetailsService,
            PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(doctorUserDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }

    @Bean
    public AuthenticationManager patientAuthenticationManager(DaoAuthenticationProvider patientAuthProvider) {
        return new ProviderManager(patientAuthProvider);
    }

    @Bean
    public AuthenticationManager doctorAuthenticationManager(DaoAuthenticationProvider doctorAuthProvider) {
        return new ProviderManager(doctorAuthProvider);
    }
}
