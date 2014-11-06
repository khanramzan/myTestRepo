/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rams.app.domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author ramazan
 */
@Entity
@Table(name = "event_table")
@NamedQueries({
    @NamedQuery(name = "EventTable.findAll", query = "SELECT e FROM EventTable e")})
public class EventTable implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_event", nullable = false)
    private Long idEvent;
    @Size(max = 45)
    @Column(name = "event_name", length = 45)
    private String eventName;
    @Column(name = "event_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date eventDate;
    @Size(max = 250)
    @Column(name = "event_poster_path", length = 250)
    private String eventPosterPath;
    @Size(max = 500)
    @Column(name = "event_comments", length = 500)
    private String eventComments;
    @OneToMany(mappedBy = "eventId")
    private Collection<Fight> fightCollection;
    @OneToMany(mappedBy = "eventMedia")
    private Collection<MediaRelation> mediaRelationCollection;
    @JoinColumn(name = "event_country", referencedColumnName = "code_country")
    @ManyToOne
    private Country eventCountry;
    @JoinColumn(name = "event_promoter", referencedColumnName = "id_promoter")
    @ManyToOne
    private Promoter eventPromoter;

    public EventTable() {
    }

    public EventTable(Long idEvent) {
        this.idEvent = idEvent;
    }

    public Long getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(Long idEvent) {
        this.idEvent = idEvent;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventPosterPath() {
        return eventPosterPath;
    }

    public void setEventPosterPath(String eventPosterPath) {
        this.eventPosterPath = eventPosterPath;
    }

    public String getEventComments() {
        return eventComments;
    }

    public void setEventComments(String eventComments) {
        this.eventComments = eventComments;
    }

    public Collection<Fight> getFightCollection() {
        return fightCollection;
    }

    public void setFightCollection(Collection<Fight> fightCollection) {
        this.fightCollection = fightCollection;
    }

    public Collection<MediaRelation> getMediaRelationCollection() {
        return mediaRelationCollection;
    }

    public void setMediaRelationCollection(Collection<MediaRelation> mediaRelationCollection) {
        this.mediaRelationCollection = mediaRelationCollection;
    }

    public Country getEventCountry() {
        return eventCountry;
    }

    public void setEventCountry(Country eventCountry) {
        this.eventCountry = eventCountry;
    }

    public Promoter getEventPromoter() {
        return eventPromoter;
    }

    public void setEventPromoter(Promoter eventPromoter) {
        this.eventPromoter = eventPromoter;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEvent != null ? idEvent.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EventTable)) {
            return false;
        }
        EventTable other = (EventTable) object;
        if ((this.idEvent == null && other.idEvent != null) || (this.idEvent != null && !this.idEvent.equals(other.idEvent))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "rams.app.domain.EventTable[ idEvent=" + idEvent + " ]";
    }
    
}
