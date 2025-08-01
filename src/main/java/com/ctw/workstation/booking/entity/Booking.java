package com.ctw.workstation.booking.entity;

import com.ctw.workstation.rack.entity.Rack;
import com.ctw.workstation.team.entity.Team;
import com.ctw.workstation.teammember.entity.TeamMember;
import jakarta.persistence.*;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name="T_BOOKING")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Schema(hidden=true)
    private UUID id;

    @Column(name = "RACK_ID")
    private UUID rackId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "RACK_ID", updatable = false, insertable = false)
    @Schema(hidden=true)
    private Rack rack;

    @Column(name = "REQUESTER_ID")
    private UUID teamMemberID;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "REQUESTER_ID", updatable = false, insertable = false)
    @Schema(hidden=true)
    private TeamMember teamMember;

    @Column(name = "BOOK_FROM", nullable = false)
    //@CreationTimestamp
    //@Schema(hidden=true)
    private LocalDateTime book_From;

    @Column(name = "BOOK_TO", updatable = false)
    //@CreationTimestamp
    //@Schema(hidden=true)
    private LocalDateTime book_To;


    @Column(name = "CREATED_AT", nullable = false, updatable = false)
    @CreationTimestamp
    @Schema(hidden=true)
    private LocalDateTime createdAt;

    @Column(name = "MODIFIED_AT", nullable = false)
    @UpdateTimestamp
    @Schema(hidden=true)
    private LocalDateTime modifiedAt;

    public Booking() {}

    public UUID getId() {
        return id;
    }

    public UUID getRackId() {
        return rackId;
    }

    public Rack getRack() {
        return rack;
    }

    public UUID getTeamMemberID() {
        return teamMemberID;
    }

    public TeamMember getTeamMember() {
        return teamMember;
    }

    public LocalDateTime getBook_From() {
        return book_From;
    }

    public LocalDateTime getBook_To() {
        return book_To;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setRackId(UUID rackId) {
        this.rackId = rackId;
    }

    public void setRack(Rack rack) {
        this.rack = rack;
    }

    public void setTeamMemberID(UUID teamMemberID) {
        this.teamMemberID = teamMemberID;
    }

    public void setTeamMember(TeamMember teamMember) {
        this.teamMember = teamMember;
    }

    public void setBook_From(LocalDateTime book_From) {
        this.book_From = book_From;
    }

    public void setBook_To(LocalDateTime book_To) {
        this.book_To = book_To;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }
}
