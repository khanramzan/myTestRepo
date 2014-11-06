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
import javax.validation.constraints.NotNull;

/**
 *
 * @author ramazan
 */
@Entity
@Table(name = "sanction_fights")
@NamedQueries({
    @NamedQuery(name = "SanctionFights.findAll", query = "SELECT s FROM SanctionFights s")})
public class SanctionFights implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_sanctionfights", nullable = false)
    private Long idSanctionfights;
    @OneToMany(mappedBy = "fightSanctionedFor")
    private Collection<Fight> fightCollection;
    @JoinColumn(name = "sanctioner", referencedColumnName = "id_sanctioner")
    @ManyToOne
    private Sanctioner sanctioner;
    @JoinColumn(name = "fight_type", referencedColumnName = "id_fight_types")
    @ManyToOne
    private FightTypes fightType;

    public SanctionFights() {
    }

    public SanctionFights(Long idSanctionfights) {
        this.idSanctionfights = idSanctionfights;
    }

    public Long getIdSanctionfights() {
        return idSanctionfights;
    }

    public void setIdSanctionfights(Long idSanctionfights) {
        this.idSanctionfights = idSanctionfights;
    }

    public Collection<Fight> getFightCollection() {
        return fightCollection;
    }

    public void setFightCollection(Collection<Fight> fightCollection) {
        this.fightCollection = fightCollection;
    }

    public Sanctioner getSanctioner() {
        return sanctioner;
    }

    public void setSanctioner(Sanctioner sanctioner) {
        this.sanctioner = sanctioner;
    }

    public FightTypes getFightType() {
        return fightType;
    }

    public void setFightType(FightTypes fightType) {
        this.fightType = fightType;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSanctionfights != null ? idSanctionfights.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SanctionFights)) {
            return false;
        }
        SanctionFights other = (SanctionFights) object;
        if ((this.idSanctionfights == null && other.idSanctionfights != null) || (this.idSanctionfights != null && !this.idSanctionfights.equals(other.idSanctionfights))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "rams.app.domain.SanctionFights[ idSanctionfights=" + idSanctionfights + " ]";
    }
    
}
