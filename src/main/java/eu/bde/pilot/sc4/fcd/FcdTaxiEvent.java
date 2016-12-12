package eu.bde.pilot.sc4.fcd;

import java.util.Locale;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;


public class FcdTaxiEvent {
	
	private static transient DateTimeFormatter timeFormatter =
			DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss.SSS");
	
	public static final String DEVICE_ID = "device_random_id";
	public static final String TIMESTAMP = "recorded_timestamp";
	public static final String LONGITUDE = "lon";
	public static final String LATITUDE = "lat";
	public static final String ALTITUDE = "altitude";
	public static final String SPEED = "speed";
	public static final String ORIENTATION = "orientation";
	public static final String TRANSFER = "transfer";
	public static final String ROAD_SEGMENT = "osmids";
	
	public int deviceId = -1;
	public DateTime timestamp;
	public double lon = 0.0;
	public double lat = 0.0;
	public double altitude = 0.0;
	public double speed = 0.0;
	public double orientation = 0.0;
	public int transfer = 0;
	public String roadSegment = null;
	
	public FcdTaxiEvent(int deviceId, 
			            DateTime timestamp, 
			            double lon, 
			            double lat, 
			            double altitude, 
			            double speed, 
			            double orientation, 
			            int transfer,
			            String roadSegment) {
		this.deviceId = deviceId;
		this.timestamp = timestamp;
		this.lon = lon;
		this.lat = lat;
		this.altitude = altitude;
		this.speed = speed;
		this.orientation = orientation;
		this.transfer = transfer;
		this.roadSegment = roadSegment;
	}

	public FcdTaxiEvent() {}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(deviceId).append("\t");
		sb.append(timestamp.toString(timeFormatter)).append("\t");
		sb.append(lon).append("\t");
		sb.append(lat).append("\t");
		sb.append(altitude).append("\t");
		sb.append(speed).append("\t");
		sb.append(orientation).append("\t");
		sb.append(transfer).append("\t");
		sb.append(roadSegment);

		return sb.toString();
	}
	
	public static FcdTaxiEvent fromString(String line) {

		String[] tokens = line.split("\t");
		if (tokens.length != 8) {
			throw new RuntimeException("Invalid record: " + line);
		}

		FcdTaxiEvent event = new FcdTaxiEvent();

		try {
			event.deviceId = Integer.parseInt(tokens[0]);
			event.timestamp = DateTime.parse(tokens[1], timeFormatter);
			event.lon = tokens[2].length() > 0 ? Double.parseDouble(tokens[2]) : 0.0;
			event.lat = tokens[3].length() > 0 ? Double.parseDouble(tokens[3]) : 0.0;
			event.altitude = tokens[4].length() > 0 ? Double.parseDouble(tokens[4]) : 0.0;
			event.speed = tokens[5].length() > 0 ? Double.parseDouble(tokens[5]) : 0.0;
			event.orientation = tokens[6].length() > 0 ? Double.parseDouble(tokens[7]) : 0.0;
			event.transfer = tokens[7].length() > 0 ? Integer.parseInt(tokens[7]) : 0;

		} catch (NumberFormatException nfe) {
			throw new RuntimeException("Invalid record: " + line, nfe);
		}

		return event;
	}
	
	@Override
	public boolean equals(Object other) {
		return other instanceof FcdTaxiEvent &&
				this.deviceId == ((FcdTaxiEvent) other).deviceId; // the deviceId is randomly generated 
	}

	@Override
	public int hashCode() {
		return (int)this.deviceId;
	}

}
