/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rams.app.domain;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author ramazan
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Sanctioner.findAll", query = "SELECT s FROM Sanctioner s")})
public class Sanctioner implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_sanctioner", nullable = false)
    private Long idSanctioner;
    @Size(max = 70)
    @Column(name = "sanctioner_name", length = 70)
    private String sanctionerName;
    @Size(max = 250)
    @Column(name = "sanctioner_address", length = 250)
    private String sanctionerAddress;
    @Size(max = 250)
    @Column(name = "sanctioner_logo_path", length = 250)
    private String sanctionerLogoPath;
    @Size(max = 500)
    @Column(name = "sanctioner_comments", length = 500)
    private String sanctionerComments;
    @OneToMany(mappedBy = "sanctionerMedia")
    private Collection<MediaRelation> mediaRelationCollection;
    @JoinColumn(name = "sanctioner_country", referencedColumnName = "code_country")
    @ManyToOne
    private Country sanctionerCountry;
    @OneToMany(mappedBy = "sanctioner")
    private Collection<SanctionFights> sanctionFightsCollection;

    public Sanctioner() {
    }

    public Sanctioner(Long idSanctioner) {
        this.idSanctioner = idSanctioner;
    }

    public Long getIdSanctioner() {
        return idSanctioner;
    }

    public void setIdSanctioner(Long idSanctioner) {
        this.idSanctioner = idSanctioner;
    }

    public String getSanctionerName() {
        return sanctionerName;
    }

    public void setSanctionerName(String sanctionerName) {
        this.sanctionerName = sanctionerName;
    }

    public String getSanctionerAddress() {
        return sanctionerAddress;
    }

    public void setSanctionerAddress(String sanctionerAddress) {
        this.sanctionerAddress = sanctionerAddress;
    }

    public String getSanctionerLogoPath() {
        return sanctionerLogoPath;
    }

    public void setSanctionerLogoPath(String sanctionerLogoPath) {
        this.sanctionerLogoPath = sanctionerLogoPath;
    }

    public String getSanctionerComments() {
        return sanctionerComments;
    }

    public void setSanctionerComments(String sanctionerComments) {
        this.sanctionerComments = sanctionerComments;
    }

    public Collection<MediaRelation> getMediaRelationCollection() {
        return mediaRelationCollection;
    }

    public void setMediaRelationCollection(Collection<MediaRelation> mediaRelationCollection) {
        this.mediaRelationCollection = mediaRelationCollection;
    }

    public Country getSanctionerCountry() {
        return sanctionerCountry;
    }

    public void setSanctionerCountry(Country sanctionerCountry) {
        this.sanctionerCountry = sanctionerCountry;
    }

    public Collection<SanctionFights> getSanctionFightsCollection() {
        return sanctionFightsCollection;
    }

    public void setSanctionFightsCollection(Collection<SanctionFights> sanctionFightsCollection) {
        this.sanctionFightsCollection = sanctionFightsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSanctioner != null ? idSanctioner.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sanctioner)) {
            return false;
        }
        Sanctioner other = (Sanctioner) object;
        if ((this.idSanctioner == null && other.idSanctioner != null) || (this.idSanctioner != null && !this.idSanctioner.equals(other.idSanctioner))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "rams.app.domain.Sanctioner[ idSanctioner=" + idSanctioner + " ]";
    }
    
}
