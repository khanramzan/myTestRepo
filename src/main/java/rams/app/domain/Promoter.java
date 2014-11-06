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
    @NamedQuery(name = "Promoter.findAll", query = "SELECT p FROM Promoter p")})
public class Promoter implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_promoter", nullable = false)
    private Long idPromoter;
    @Size(max = 150)
    @Column(name = "promoter_name", length = 150)
    private String promoterName;
    @Size(max = 250)
    @Column(name = "promoter_address", length = 250)
    private String promoterAddress;
    @Size(max = 250)
    @Column(name = "promoter_poster_path", length = 250)
    private String promoterPosterPath;
    @Size(max = 500)
    @Column(name = "promoter_comments", length = 500)
    private String promoterComments;
    @JoinColumn(name = "promoter_country", referencedColumnName = "code_country")
    @ManyToOne
    private Country promoterCountry;
    @OneToMany(mappedBy = "promoterMedia")
    private Collection<MediaRelation> mediaRelationCollection;
    @OneToMany(mappedBy = "eventPromoter")
    private Collection<EventTable> eventTableCollection;

    public Promoter() {
    }

    public Promoter(Long idPromoter) {
        this.idPromoter = idPromoter;
    }

    public Long getIdPromoter() {
        return idPromoter;
    }

    public void setIdPromoter(Long idPromoter) {
        this.idPromoter = idPromoter;
    }

    public String getPromoterName() {
        return promoterName;
    }

    public void setPromoterName(String promoterName) {
        this.promoterName = promoterName;
    }

    public String getPromoterAddress() {
        return promoterAddress;
    }

    public void setPromoterAddress(String promoterAddress) {
        this.promoterAddress = promoterAddress;
    }

    public String getPromoterPosterPath() {
        return promoterPosterPath;
    }

    public void setPromoterPosterPath(String promoterPosterPath) {
        this.promoterPosterPath = promoterPosterPath;
    }

    public String getPromoterComments() {
        return promoterComments;
    }

    public void setPromoterComments(String promoterComments) {
        this.promoterComments = promoterComments;
    }

    public Country getPromoterCountry() {
        return promoterCountry;
    }

    public void setPromoterCountry(Country promoterCountry) {
        this.promoterCountry = promoterCountry;
    }

    public Collection<MediaRelation> getMediaRelationCollection() {
        return mediaRelationCollection;
    }

    public void setMediaRelationCollection(Collection<MediaRelation> mediaRelationCollection) {
        this.mediaRelationCollection = mediaRelationCollection;
    }

    public Collection<EventTable> getEventTableCollection() {
        return eventTableCollection;
    }

    public void setEventTableCollection(Collection<EventTable> eventTableCollection) {
        this.eventTableCollection = eventTableCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPromoter != null ? idPromoter.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Promoter)) {
            return false;
        }
        Promoter other = (Promoter) object;
        if ((this.idPromoter == null && other.idPromoter != null) || (this.idPromoter != null && !this.idPromoter.equals(other.idPromoter))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "rams.app.domain.Promoter[ idPromoter=" + idPromoter + " ]";
    }
    
}
