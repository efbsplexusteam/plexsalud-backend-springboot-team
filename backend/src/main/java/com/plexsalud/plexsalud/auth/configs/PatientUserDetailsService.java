package com.plexsalud.plexsalud.auth.configs;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.plexsalud.plexsalud.patient.repositories.PatientRepository;

@Service("patientUserDetailsService")
public class PatientUserDetailsService implements UserDetailsService {

    private final PatientRepository patientRepository;

    public PatientUserDetailsService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return patientRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Patient not found"));
    }
}
