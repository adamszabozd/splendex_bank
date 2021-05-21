package com.example.splendex_bank.security;

import com.example.splendex_bank.domain.Account;
import com.example.splendex_bank.domain.AccountRole;
import com.example.splendex_bank.repository.AccountRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class JPAUserDetailsService implements UserDetailsService {

    private final AccountRepository accountRepository;

    public JPAUserDetailsService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Account> optionalAccount = accountRepository.findByUsername(username);

        if (optionalAccount.isPresent()) {
            Account account = optionalAccount.get();
            String[] accountRoles = account.getAccountRoleList()
                    .stream().map(AccountRole::name).toArray(String[]::new);
            List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(accountRoles);
            return User.withUsername(username).authorities(authorities).password(account.getEncryptedPassword()).build();
        } else {
            throw new UsernameNotFoundException("No account was found with the following username: " + username);
        }
    }
}
