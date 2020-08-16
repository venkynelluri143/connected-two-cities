package com.walmart.gai.dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity 
@Table(name = "API_CONSUMER_KEYS")

public class ApiConsumerKeys implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "PROCESS_ID")
	private String processId; 
	
	@Column(name = "CONSUMER")
	private String consumer;

	@Column(name = "CONSUMER_DL")
	private String consumerDl;
	
	@Column(name = "PUBLIC_KEY")
	private String publicKey;
	
	@Column(name = "CREATED_TS")
	private String createdTs;
	
	@Column(name = "UPDATED_TS")
	private String updatedTs;

	public String getProcessId() {
		return processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}

	public String getConsumer() {
		return consumer;
	}

	public void setConsumer(String consumer) {
		this.consumer = consumer;
	}

	public String getConsumerDl() {
		return consumerDl;
	}

	public void setConsumerDl(String consumerDl) {
		this.consumerDl = consumerDl;
	}

	public String getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}

	public String getCreatedTs() {
		return createdTs;
	}

	public void setCreatedTs(String createdTs) {
		this.createdTs = createdTs;
	}

	public String getUpdatedTs() {
		return updatedTs;
	}

	public void setUpdatedTs(String updatedTs) {
		this.updatedTs = updatedTs;
	}

	@Override
	public String toString() {
		return "ApiConsumerKeys [processId=" + processId + ", consumer=" + consumer + ", consumerDl=" + consumerDl
				+ ", publicKey=" + publicKey + ", createdTs=" + createdTs + ", updatedTs=" + updatedTs + "]";
	}
}
