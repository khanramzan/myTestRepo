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
@Table(name = "weight_catagory")
@NamedQueries({
    @NamedQuery(name = "WeightCatagory.findAll", query = "SELECT w FROM WeightCatagory w")})
public class WeightCatagory implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_weight_catagory", nullable = false)
    private Long idWeightCatagory;
    @Size(max = 60)
    @Column(name = "weight_catagory", length = 60)
    private String weightCatagory;
    @Size(max = 255)
    @Column(name = "weight_catagory_comments", length = 255)
    private String weightCatagoryComments;
    @OneToMany(mappedBy = "weightCatagoryId")
    private Collection<Fight> fightCollection;

    public WeightCatagory() {
    }

    public WeightCatagory(Long idWeightCatagory) {
        this.idWeightCatagory = idWeightCatagory;
    }

    public Long getIdWeightCatagory() {
        return idWeightCatagory;
    }

    public void setIdWeightCatagory(Long idWeightCatagory) {
        this.idWeightCatagory = idWeightCatagory;
    }

    public String getWeightCatagory() {
        return weightCatagory;
    }

    public void setWeightCatagory(String weightCatagory) {
        this.weightCatagory = weightCatagory;
    }

    public String getWeightCatagoryComments() {
        return weightCatagoryComments;
    }

    public void setWeightCatagoryComments(String weightCatagoryComments) {
        this.weightCatagoryComments = weightCatagoryComments;
    }

    public Collection<Fight> getFightCollection() {
        return fightCollection;
    }

    public void setFightCollection(Collection<Fight> fightCollection) {
        this.fightCollection = fightCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idWeightCatagory != null ? idWeightCatagory.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WeightCatagory)) {
            return false;
        }
        WeightCatagory other = (WeightCatagory) object;
        if ((this.idWeightCatagory == null && other.idWeightCatagory != null) || (this.idWeightCatagory != null && !this.idWeightCatagory.equals(other.idWeightCatagory))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "rams.app.domain.WeightCatagory[ idWeightCatagory=" + idWeightCatagory + " ]";
    }
    
}
