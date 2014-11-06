/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rams.app.domain;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author ramazan
 */
@Entity
@Table(name = "media_table")
@NamedQueries({
    @NamedQuery(name = "MediaTable.findAll", query = "SELECT m FROM MediaTable m")})
public class MediaTable implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_media", nullable = false)
    private Long idMedia;
    @Column(name = "media_type")
    private BigInteger mediaType;
    @Size(max = 150)
    @Column(name = "media_name", length = 150)
    private String mediaName;
    @Size(max = 150)
    @Column(name = "media_path", length = 150)
    private String mediaPath;
    @Lob
    @Column(name = "media_on_server")
    private byte[] mediaOnServer;
    @OneToMany(mappedBy = "mediaId")
    private Collection<MediaRelation> mediaRelationCollection;

    public MediaTable() {
    }

    public MediaTable(Long idMedia) {
        this.idMedia = idMedia;
    }

    public Long getIdMedia() {
        return idMedia;
    }

    public void setIdMedia(Long idMedia) {
        this.idMedia = idMedia;
    }

    public BigInteger getMediaType() {
        return mediaType;
    }

    public void setMediaType(BigInteger mediaType) {
        this.mediaType = mediaType;
    }

    public String getMediaName() {
        return mediaName;
    }

    public void setMediaName(String mediaName) {
        this.mediaName = mediaName;
    }

    public String getMediaPath() {
        return mediaPath;
    }

    public void setMediaPath(String mediaPath) {
        this.mediaPath = mediaPath;
    }

    public byte[] getMediaOnServer() {
        return mediaOnServer;
    }

    public void setMediaOnServer(byte[] mediaOnServer) {
        this.mediaOnServer = mediaOnServer;
    }

    public Collection<MediaRelation> getMediaRelationCollection() {
        return mediaRelationCollection;
    }

    public void setMediaRelationCollection(Collection<MediaRelation> mediaRelationCollection) {
        this.mediaRelationCollection = mediaRelationCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMedia != null ? idMedia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MediaTable)) {
            return false;
        }
        MediaTable other = (MediaTable) object;
        if ((this.idMedia == null && other.idMedia != null) || (this.idMedia != null && !this.idMedia.equals(other.idMedia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "rams.app.domain.MediaTable[ idMedia=" + idMedia + " ]";
    }
    
}
