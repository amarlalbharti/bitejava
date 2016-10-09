package com.bharti.constraints;

import java.util.Comparator;

import com.bharti.domain.Keynote;

public class KeynoteCompare implements Comparator<Keynote> {

	@Override
	public int compare(Keynote kn1, Keynote kn2) {
		
		return kn1.getDisplayOrder() - kn1.getDisplayOrder();
	}

}
