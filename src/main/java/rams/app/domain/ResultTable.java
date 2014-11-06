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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author ramazan
 */
@Entity
@Table(name = "result_table")
@NamedQueries({
    @NamedQuery(name = "ResultTable.findAll", query = "SELECT r FROM ResultTable r")})
public class ResultTable implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_result", nullable = false)
    private Long idResult;
    @Size(max = 80)
    @Column(name = "announced_result", length = 80)
    private String announcedResult;
    private BigInteger winner;
    @Size(max = 250)
    @Column(name = "result_comment", length = 250)
    private String resultComment;
    @OneToMany(mappedBy = "resultsMedia")
    private Collection<MediaRelation> mediaRelationCollection;
    @JoinColumn(name = "id_result", referencedColumnName = "id_fight", nullable = false, insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Fight fight;

    public ResultTable() {
    }

    public ResultTable(Long idResult) {
        this.idResult = idResult;
    }

    public Long getIdResult() {
        return idResult;
    }

    public void setIdResult(Long idResult) {
        this.idResult = idResult;
    }

    public String getAnnouncedResult() {
        return announcedResult;
    }

    public void setAnnouncedResult(String announcedResult) {
        this.announcedResult = announcedResult;
    }

    public BigInteger getWinner() {
        return winner;
    }

    public void setWinner(BigInteger winner) {
        this.winner = winner;
    }

    public String getResultComment() {
        return resultComment;
    }

    public void setResultComment(String resultComment) {
        this.resultComment = resultComment;
    }

    public Collection<MediaRelation> getMediaRelationCollection() {
        return mediaRelationCollection;
    }

    public void setMediaRelationCollection(Collection<MediaRelation> mediaRelationCollection) {
        this.mediaRelationCollection = mediaRelationCollection;
    }

    public Fight getFight() {
        return fight;
    }

    public void setFight(Fight fight) {
        this.fight = fight;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idResult != null ? idResult.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ResultTable)) {
            return false;
        }
        ResultTable other = (ResultTable) object;
        if ((this.idResult == null && other.idResult != null) || (this.idResult != null && !this.idResult.equals(other.idResult))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "rams.app.domain.ResultTable[ idResult=" + idResult + " ]";
    }
    
}
