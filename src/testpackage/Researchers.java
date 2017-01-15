/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testpackage;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author Christos
 */
@Entity
@Table(name = "researchers", catalog = "kios", schema = "")
@NamedQueries({
    @NamedQuery(name = "Researchers.findAll", query = "SELECT r FROM Researchers r")
    , @NamedQuery(name = "Researchers.findById", query = "SELECT r FROM Researchers r WHERE r.id = :id")
    , @NamedQuery(name = "Researchers.findByFullName", query = "SELECT r FROM Researchers r WHERE r.fullName = :fullName")
    , @NamedQuery(name = "Researchers.findByFkSupervisor", query = "SELECT r FROM Researchers r WHERE r.fkSupervisor = :fkSupervisor")
    , @NamedQuery(name = "Researchers.findByAddress", query = "SELECT r FROM Researchers r WHERE r.address = :address")
    , @NamedQuery(name = "Researchers.findByTelephone", query = "SELECT r FROM Researchers r WHERE r.telephone = :telephone")
    , @NamedQuery(name = "Researchers.findByEmail", query = "SELECT r FROM Researchers r WHERE r.email = :email")
    , @NamedQuery(name = "Researchers.findByAccessToKios", query = "SELECT r FROM Researchers r WHERE r.accessToKios = :accessToKios")
    , @NamedQuery(name = "Researchers.findByOfficeNumber", query = "SELECT r FROM Researchers r WHERE r.officeNumber = :officeNumber")
    , @NamedQuery(name = "Researchers.findByActive", query = "SELECT r FROM Researchers r WHERE r.active = :active")
    , @NamedQuery(name = "Researchers.findByDetails", query = "SELECT r FROM Researchers r WHERE r.details = :details")
    , @NamedQuery(name = "Researchers.findBySupervisor2", query = "SELECT r FROM Researchers r WHERE r.supervisor2 = :supervisor2")
    , @NamedQuery(name = "Researchers.findBySerialNo", query = "SELECT r FROM Researchers r WHERE r.serialNo = :serialNo")})
public class Researchers implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private String id;
    @Basic(optional = false)
    @Column(name = "FullName")
    private String fullName;
    @Basic(optional = false)
    @Column(name = "fk_Supervisor")
    private int fkSupervisor;
    @Column(name = "Address")
    private String address;
    @Column(name = "Telephone")
    private String telephone;
    @Column(name = "Email")
    private String email;
    @Column(name = "AccessToKios")
    private Boolean accessToKios;
    @Column(name = "OfficeNumber")
    private String officeNumber;
    @Basic(optional = false)
    @Column(name = "Active")
    private boolean active;
    @Column(name = "Details")
    private String details;
    @Column(name = "supervisor2")
    private Integer supervisor2;
    @Column(name = "SerialNo")
    private String serialNo;

    public Researchers() {
    }

    public Researchers(String id) {
        this.id = id;
    }

    public Researchers(String id, String fullName, int fkSupervisor, boolean active) {
        this.id = id;
        this.fullName = fullName;
        this.fkSupervisor = fkSupervisor;
        this.active = active;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        String oldId = this.id;
        this.id = id;
        changeSupport.firePropertyChange("id", oldId, id);
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        String oldFullName = this.fullName;
        this.fullName = fullName;
        changeSupport.firePropertyChange("fullName", oldFullName, fullName);
    }

    public int getFkSupervisor() {
        return fkSupervisor;
    }

    public void setFkSupervisor(int fkSupervisor) {
        int oldFkSupervisor = this.fkSupervisor;
        this.fkSupervisor = fkSupervisor;
        changeSupport.firePropertyChange("fkSupervisor", oldFkSupervisor, fkSupervisor);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        String oldAddress = this.address;
        this.address = address;
        changeSupport.firePropertyChange("address", oldAddress, address);
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        String oldTelephone = this.telephone;
        this.telephone = telephone;
        changeSupport.firePropertyChange("telephone", oldTelephone, telephone);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        String oldEmail = this.email;
        this.email = email;
        changeSupport.firePropertyChange("email", oldEmail, email);
    }

    public Boolean getAccessToKios() {
        return accessToKios;
    }

    public void setAccessToKios(Boolean accessToKios) {
        Boolean oldAccessToKios = this.accessToKios;
        this.accessToKios = accessToKios;
        changeSupport.firePropertyChange("accessToKios", oldAccessToKios, accessToKios);
    }

    public String getOfficeNumber() {
        return officeNumber;
    }

    public void setOfficeNumber(String officeNumber) {
        String oldOfficeNumber = this.officeNumber;
        this.officeNumber = officeNumber;
        changeSupport.firePropertyChange("officeNumber", oldOfficeNumber, officeNumber);
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        boolean oldActive = this.active;
        this.active = active;
        changeSupport.firePropertyChange("active", oldActive, active);
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        String oldDetails = this.details;
        this.details = details;
        changeSupport.firePropertyChange("details", oldDetails, details);
    }

    public Integer getSupervisor2() {
        return supervisor2;
    }

    public void setSupervisor2(Integer supervisor2) {
        Integer oldSupervisor2 = this.supervisor2;
        this.supervisor2 = supervisor2;
        changeSupport.firePropertyChange("supervisor2", oldSupervisor2, supervisor2);
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        String oldSerialNo = this.serialNo;
        this.serialNo = serialNo;
        changeSupport.firePropertyChange("serialNo", oldSerialNo, serialNo);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Researchers)) {
            return false;
        }
        Researchers other = (Researchers) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "testpackage.Researchers[ id=" + id + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
