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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author ramazan
 */
@Entity
@Table(name = "media_relation")
@NamedQueries({
    @NamedQuery(name = "MediaRelation.findAll", query = "SELECT m FROM MediaRelation m")})
public class MediaRelation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_media_relation", nullable = false)
    private Long idMediaRelation;
    @Lob
    @Size(max = 65535)
    @Column(name = "media_comments", length = 65535)
    private String mediaComments;
    @JoinColumn(name = "media_id", referencedColumnName = "id_media")
    @ManyToOne
    private MediaTable mediaId;
    @JoinColumn(name = "results_media", referencedColumnName = "id_result")
    @ManyToOne
    private ResultTable resultsMedia;
    @JoinColumn(name = "promoter_media", referencedColumnName = "id_promoter")
    @ManyToOne
    private Promoter promoterMedia;
    @JoinColumn(name = "sanctioner_media", referencedColumnName = "id_sanctioner")
    @ManyToOne
    private Sanctioner sanctionerMedia;
    @JoinColumn(name = "fight_media", referencedColumnName = "id_fight")
    @ManyToOne
    private Fight fightMedia;
    @JoinColumn(name = "event_media", referencedColumnName = "id_event")
    @ManyToOne
    private EventTable eventMedia;
    @JoinColumn(name = "fighter_media", referencedColumnName = "id_fighter")
    @ManyToOne
    private Fighter fighterMedia;

    public MediaRelation() {
    }

    public MediaRelation(Long idMediaRelation) {
        this.idMediaRelation = idMediaRelation;
    }

    public Long getIdMediaRelation() {
        return idMediaRelation;
    }

    public void setIdMediaRelation(Long idMediaRelation) {
        this.idMediaRelation = idMediaRelation;
    }

    public String getMediaComments() {
        return mediaComments;
    }

    public void setMediaComments(String mediaComments) {
        this.mediaComments = mediaComments;
    }

    public MediaTable getMediaId() {
        return mediaId;
    }

    public void setMediaId(MediaTable mediaId) {
        this.mediaId = mediaId;
    }

    public ResultTable getResultsMedia() {
        return resultsMedia;
    }

    public void setResultsMedia(ResultTable resultsMedia) {
        this.resultsMedia = resultsMedia;
    }

    public Promoter getPromoterMedia() {
        return promoterMedia;
    }

    public void setPromoterMedia(Promoter promoterMedia) {
        this.promoterMedia = promoterMedia;
    }

    public Sanctioner getSanctionerMedia() {
        return sanctionerMedia;
    }

    public void setSanctionerMedia(Sanctioner sanctionerMedia) {
        this.sanctionerMedia = sanctionerMedia;
    }

    public Fight getFightMedia() {
        return fightMedia;
    }

    public void setFightMedia(Fight fightMedia) {
        this.fightMedia = fightMedia;
    }

    public EventTable getEventMedia() {
        return eventMedia;
    }

    public void setEventMedia(EventTable eventMedia) {
        this.eventMedia = eventMedia;
    }

    public Fighter getFighterMedia() {
        return fighterMedia;
    }

    public void setFighterMedia(Fighter fighterMedia) {
        this.fighterMedia = fighterMedia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMediaRelation != null ? idMediaRelation.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MediaRelation)) {
            return false;
        }
        MediaRelation other = (MediaRelation) object;
        if ((this.idMediaRelation == null && other.idMediaRelation != null) || (this.idMediaRelation != null && !this.idMediaRelation.equals(other.idMediaRelation))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "rams.app.domain.MediaRelation[ idMediaRelation=" + idMediaRelation + " ]";
    }
    
}
