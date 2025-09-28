package com.plexsalud.plexsalud.auth.configs;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.plexsalud.plexsalud.doctor.repositories.DoctorRepository;

@Service("doctorUserDetailsService")
public class DoctorUserDetailsService implements UserDetailsService {

    private final DoctorRepository doctorRepository;

    public DoctorUserDetailsService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return doctorRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Doctor not found"));
    }
}