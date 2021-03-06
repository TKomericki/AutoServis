package opp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private DataSource dataSource;

    @Value("${spring.queries.users-query}")
    private String usersQuery;

    @Value("${spring.queries.roles-query}")
    private String rolesQuery;

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.
                jdbcAuthentication()
                .usersByUsernameQuery(usersQuery)
                .authoritiesByUsernameQuery(rolesQuery)
                .dataSource(dataSource)
                .passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.
                authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/pocetna").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/zaboravljenaLozinka").permitAll()
                .antMatchers("/registration").permitAll()
                .antMatchers("/serviserRegistration").hasAuthority("ADMIN")
                .antMatchers("/createNewServiser").hasAuthority("ADMIN")
                .antMatchers("/editKorisnik").hasAuthority("ADMIN")
                .antMatchers("/editPrijava").hasAnyAuthority("KORISNIK", "SERVISER")
                //.antMatchers("/editPrijava").hasAuthority("SERVISER")
                .antMatchers("/ispis").authenticated()
                .antMatchers("/registracijaServisera").hasAuthority("ADMIN")
                .antMatchers("/popravak").hasAuthority("KORISNIK")
                .antMatchers("/popravak2").hasAuthority("KORISNIK")
                .antMatchers("/prijavaPopravka").hasAuthority("KORISNIK")
                .antMatchers("/prihvatiPopravak").hasAuthority("SERVISER")
                .antMatchers("/popravakZavrsen").hasAuthority("SERVISER")
                .antMatchers("/korisnikPromijenjen").authenticated()
                .antMatchers("/admin/**").hasAuthority("ADMIN").anyRequest()
                .authenticated().and().csrf().disable().formLogin()
                .loginPage("/login").failureUrl("/login?error=true")
                .defaultSuccessUrl("/ispis")
                .usernameParameter("email")
                .passwordParameter("password")
                .and().logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/").and().exceptionHandling()
                .accessDeniedPage("/access-denied");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
    }

}
