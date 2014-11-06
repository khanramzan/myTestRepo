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
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author ramazan
 */
@Entity
@Table(uniqueConstraints = {
    @UniqueConstraint(columnNames = {"id_fighter"})})
@NamedQueries({
    @NamedQuery(name = "Fighter.findAll", query = "SELECT f FROM Fighter f")})
public class Fighter implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_fighter", nullable = false)
    private Long idFighter;
    @Size(max = 150)
    @Column(name = "fighter_name", length = 150)
    private String fighterName;
    @Size(max = 100)
    @Column(name = "fighter_club", length = 100)
    private String fighterClub;
    @Size(max = 250)
    @Column(name = "fighter_profile_pic_path", length = 250)
    private String fighterProfilePicPath;
    @Size(max = 500)
    @Column(name = "fight_comments", length = 500)
    private String fightComments;
    @OneToMany(mappedBy = "fighterNo2")
    private Collection<Fight> fightCollection;
    @OneToMany(mappedBy = "fighterNo1")
    private Collection<Fight> fightCollection1;
    @OneToMany(mappedBy = "fighterMedia")
    private Collection<MediaRelation> mediaRelationCollection;
    @JoinColumn(name = "fight_country", referencedColumnName = "code_country")
    @ManyToOne
    private Country fightCountry;

    public Fighter() {
    }

    public Fighter(Long idFighter) {
        this.idFighter = idFighter;
    }

    public Long getIdFighter() {
        return idFighter;
    }

    public void setIdFighter(Long idFighter) {
        this.idFighter = idFighter;
    }

    public String getFighterName() {
        return fighterName;
    }

    public void setFighterName(String fighterName) {
        this.fighterName = fighterName;
    }

    public String getFighterClub() {
        return fighterClub;
    }

    public void setFighterClub(String fighterClub) {
        this.fighterClub = fighterClub;
    }

    public String getFighterProfilePicPath() {
        return fighterProfilePicPath;
    }

    public void setFighterProfilePicPath(String fighterProfilePicPath) {
        this.fighterProfilePicPath = fighterProfilePicPath;
    }

    public String getFightComments() {
        return fightComments;
    }

    public void setFightComments(String fightComments) {
        this.fightComments = fightComments;
    }

    public Collection<Fight> getFightCollection() {
        return fightCollection;
    }

    public void setFightCollection(Collection<Fight> fightCollection) {
        this.fightCollection = fightCollection;
    }

    public Collection<Fight> getFightCollection1() {
        return fightCollection1;
    }

    public void setFightCollection1(Collection<Fight> fightCollection1) {
        this.fightCollection1 = fightCollection1;
    }

    public Collection<MediaRelation> getMediaRelationCollection() {
        return mediaRelationCollection;
    }

    public void setMediaRelationCollection(Collection<MediaRelation> mediaRelationCollection) {
        this.mediaRelationCollection = mediaRelationCollection;
    }

    public Country getFightCountry() {
        return fightCountry;
    }

    public void setFightCountry(Country fightCountry) {
        this.fightCountry = fightCountry;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFighter != null ? idFighter.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Fighter)) {
            return false;
        }
        Fighter other = (Fighter) object;
        if ((this.idFighter == null && other.idFighter != null) || (this.idFighter != null && !this.idFighter.equals(other.idFighter))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "rams.app.domain.Fighter[ idFighter=" + idFighter + " ]";
    }
    
}
