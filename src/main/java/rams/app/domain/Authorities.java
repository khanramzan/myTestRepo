/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rams.app.domain;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author ramazan
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Authorities.findAll", query = "SELECT a FROM Authorities a"),
    @NamedQuery(name="Authorities.findById", query = "SELECT a FROM Authorities a WHERE a.idAuthorities=:ff")
})
public class Authorities implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_authorities", nullable = false)
    private Long idAuthorities;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(nullable = false, length = 50)
    private String authority;
    @JoinColumn(name = "username", referencedColumnName = "username", nullable = false)
    @ManyToOne(optional = false)
    private Users username;

    public Authorities() {
    }

    public Authorities(Long idAuthorities) {
        this.idAuthorities = idAuthorities;
    }

    public Authorities(Long idAuthorities, String authority) {
        this.idAuthorities = idAuthorities;
        this.authority = authority;
    }

    public Long getIdAuthorities() {
        return idAuthorities;
    }

    public void setIdAuthorities(Long idAuthorities) {
        this.idAuthorities = idAuthorities;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public Users getUsername() {
        return username;
    }

    public void setUsername(Users username) {
        this.username = username;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAuthorities != null ? idAuthorities.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Authorities)) {
            return false;
        }
        Authorities other = (Authorities) object;
        if ((this.idAuthorities == null && other.idAuthorities != null) || (this.idAuthorities != null && !this.idAuthorities.equals(other.idAuthorities))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "rams.app.domain.Authorities[ idAuthorities=" + idAuthorities + " ]";
    }
    
}
