package com.learning.java8plus;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TimezoneDisplay {

	private String getOffset(LocalDateTime dateTime, ZoneId id) {
		return dateTime.atZone(id).getOffset().getId().replace("Z", "+00:00");
	}

	public List<String> getTimeZoneList(OffsetBase base) {

		LocalDateTime now = LocalDateTime.now();
		 ZoneId.getAvailableZoneIds().stream().map(ZoneId::of).sorted(new ZoneComparator())
				.map(id -> String.format("(%s%s) %s", base, getOffset(now, id), id.getId())).map(" "::split)
				.flatMap(Arrays::stream).forEach(st->
				System.out.println(st))

		;
		return null;
	}

	public enum OffsetBase {
		UTC
	}

	private class ZoneComparator implements Comparator<ZoneId> {

		@Override
		public int compare(ZoneId zoneId1, ZoneId zoneId2) {
			LocalDateTime now = LocalDateTime.now();
			ZoneOffset offset1 = now.atZone(zoneId1).getOffset();
			ZoneOffset offset2 = now.atZone(zoneId2).getOffset();

			return offset1.compareTo(offset2);
		}
	}

}
