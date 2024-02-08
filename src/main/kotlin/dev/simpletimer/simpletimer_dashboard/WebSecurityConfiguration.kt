package dev.simpletimer.simpletimer_dashboard

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.DefaultSecurityFilterChain

/**
 * Webページのセキュリティの設定を行っています。
 *
 * @author mqrimo
 */
@Configuration
@EnableWebSecurity
class WebSecurityConfiguration {
    @Bean
    fun filterChain(httpSecurity: HttpSecurity): DefaultSecurityFilterChain = httpSecurity
        //OAuth2のログイン時の設定を行っています。
        .oauth2Login { loginConfig ->
            loginConfig.defaultSuccessUrl("/", true)
        }
        //ログアウト時の設定を行っています。
        .logout { logoutConfig ->
            logoutConfig.logoutSuccessUrl("/")
        }
        //各ページの権限を設定しています。
        .authorizeHttpRequests { authRequestConfig ->
            //公開ページ・資産
            authRequestConfig.requestMatchers("/").permitAll()
            authRequestConfig.requestMatchers("/js/common.js").permitAll()
            authRequestConfig.requestMatchers("/css/common.css").permitAll()
            //認証が必要
            authRequestConfig.anyRequest().authenticated()
        }.build()
}