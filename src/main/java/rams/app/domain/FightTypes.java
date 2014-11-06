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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author ramazan
 */
@Entity
@Table(name = "fight_types")
@NamedQueries({
    @NamedQuery(name = "FightTypes.findAll", query = "SELECT f FROM FightTypes f")})
public class FightTypes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_fight_types", nullable = false)
    private Long idFightTypes;
    @Size(max = 45)
    @Column(name = "fight_type", length = 45)
    private String fightType;
    @Size(max = 250)
    @Column(name = "fight_type_description", length = 250)
    private String fightTypeDescription;
    @OneToMany(mappedBy = "fightType")
    private Collection<SanctionFights> sanctionFightsCollection;

    public FightTypes() {
    }

    public FightTypes(Long idFightTypes) {
        this.idFightTypes = idFightTypes;
    }

    public Long getIdFightTypes() {
        return idFightTypes;
    }

    public void setIdFightTypes(Long idFightTypes) {
        this.idFightTypes = idFightTypes;
    }

    public String getFightType() {
        return fightType;
    }

    public void setFightType(String fightType) {
        this.fightType = fightType;
    }

    public String getFightTypeDescription() {
        return fightTypeDescription;
    }

    public void setFightTypeDescription(String fightTypeDescription) {
        this.fightTypeDescription = fightTypeDescription;
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
        hash += (idFightTypes != null ? idFightTypes.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FightTypes)) {
            return false;
        }
        FightTypes other = (FightTypes) object;
        if ((this.idFightTypes == null && other.idFightTypes != null) || (this.idFightTypes != null && !this.idFightTypes.equals(other.idFightTypes))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "rams.app.domain.FightTypes[ idFightTypes=" + idFightTypes + " ]";
    }
    
}
