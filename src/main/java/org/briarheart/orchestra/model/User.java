package org.briarheart.orchestra.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

/**
 * @author Roman Chigvintsev
 */
@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Table("users")
public class User implements UserDetails {
    @Id
    private Long id;
    @NotBlank
    @Size(max = 255)
    private String email;
    @Builder.Default
    private boolean emailConfirmed = true;
    @Version
    private long version;
    @Size(max = 50)
    private String password;
    @Builder.Default
    private boolean admin = false;
    @Builder.Default
    private boolean enabled = true;
    @Size(max = 255)
    private String fullName;
    @Size(max = 2_000)
    private String profilePictureUrl;
    @Builder.Default
    @Transient
    private Collection<? extends GrantedAuthority> authorities = Collections.emptySet();

    /**
     * Creates copy of the given user.
     *
     * @param user user to be copied (must not be {@code null})
     */
    public User(User user) {
        Assert.notNull(user, "User must not be null");
        this.id = user.id;
        this.email = user.email;
        this.emailConfirmed = user.emailConfirmed;
        this.version = user.version;
        this.password = user.password;
        this.admin = user.admin;
        this.enabled = user.enabled;
        this.fullName = user.fullName;
        this.profilePictureUrl = user.profilePictureUrl;

        if (!CollectionUtils.isEmpty(user.authorities)) {
            this.authorities = new HashSet<>(user.authorities);
        } else {
            this.authorities = Collections.emptySet();
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    /**
     * Alias for {@link #getEmail()}.
     *
     * @return user email
     */
    @Override
    public String getUsername() {
        return getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
