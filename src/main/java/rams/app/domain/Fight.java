/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rams.app.domain;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author ramazan
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Fight.findAll", query = "SELECT f FROM Fight f")})
public class Fight implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_fight", nullable = false)
    private Long idFight;
    @Size(max = 500)
    @Column(name = "fight_comments", length = 500)
    private String fightComments;
    @JoinColumn(name = "weight_catagory_id", referencedColumnName = "id_weight_catagory")
    @ManyToOne
    private WeightCatagory weightCatagoryId;
    @JoinColumn(name = "fight_sanctioned_for", referencedColumnName = "id_sanctionfights")
    @ManyToOne
    private SanctionFights fightSanctionedFor;
    @JoinColumn(name = "fighter_no2", referencedColumnName = "id_fighter")
    @ManyToOne
    private Fighter fighterNo2;
    @JoinColumn(name = "fighter_no1", referencedColumnName = "id_fighter")
    @ManyToOne
    private Fighter fighterNo1;
    @JoinColumn(name = "event_id", referencedColumnName = "id_event")
    @ManyToOne
    private EventTable eventId;
    @OneToMany(mappedBy = "fightMedia")
    private Collection<MediaRelation> mediaRelationCollection;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "fight")
    private ResultTable resultTable;

    public Fight() {
    }

    public Fight(Long idFight) {
        this.idFight = idFight;
    }

    public Long getIdFight() {
        return idFight;
    }

    public void setIdFight(Long idFight) {
        this.idFight = idFight;
    }

    public String getFightComments() {
        return fightComments;
    }

    public void setFightComments(String fightComments) {
        this.fightComments = fightComments;
    }

    public WeightCatagory getWeightCatagoryId() {
        return weightCatagoryId;
    }

    public void setWeightCatagoryId(WeightCatagory weightCatagoryId) {
        this.weightCatagoryId = weightCatagoryId;
    }

    public SanctionFights getFightSanctionedFor() {
        return fightSanctionedFor;
    }

    public void setFightSanctionedFor(SanctionFights fightSanctionedFor) {
        this.fightSanctionedFor = fightSanctionedFor;
    }

    public Fighter getFighterNo2() {
        return fighterNo2;
    }

    public void setFighterNo2(Fighter fighterNo2) {
        this.fighterNo2 = fighterNo2;
    }

    public Fighter getFighterNo1() {
        return fighterNo1;
    }

    public void setFighterNo1(Fighter fighterNo1) {
        this.fighterNo1 = fighterNo1;
    }

    public EventTable getEventId() {
        return eventId;
    }

    public void setEventId(EventTable eventId) {
        this.eventId = eventId;
    }

    public Collection<MediaRelation> getMediaRelationCollection() {
        return mediaRelationCollection;
    }

    public void setMediaRelationCollection(Collection<MediaRelation> mediaRelationCollection) {
        this.mediaRelationCollection = mediaRelationCollection;
    }

    public ResultTable getResultTable() {
        return resultTable;
    }

    public void setResultTable(ResultTable resultTable) {
        this.resultTable = resultTable;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFight != null ? idFight.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Fight)) {
            return false;
        }
        Fight other = (Fight) object;
        if ((this.idFight == null && other.idFight != null) || (this.idFight != null && !this.idFight.equals(other.idFight))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "rams.app.domain.Fight[ idFight=" + idFight + " ]";
    }
    
}
