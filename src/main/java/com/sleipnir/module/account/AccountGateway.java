package com.sleipnir.module.account;

import java.util.List;

import org.jooq.Condition;
import org.jooq.Record;
import org.jooq.generated.tables.records.MAccountRecord;

import com.sleipnir.core.BasicGatewayImpl;

public class AccountGateway extends BasicGatewayImpl<Long, MAccountRecord>{

	@Override
	public Record retrieve(Long key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Record> retrieve() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Record> retrieve(int limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Record> retrieve(Condition condition) {
		// TODO Auto-generated method stub
		return null;
	}

}
