package com.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "attachment")
public class Attachment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "attachment_name")
    private String attachmentName;

    @Column(name = "attachment_path")
    private String attachmentPath;

    @Column(name = "attachment_type")
    private String attachmentType;

    @OneToOne(fetch = FetchType.LAZY,mappedBy = "attachment")
    private User user;

}
