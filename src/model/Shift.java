package model;

import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.pmw.tinylog.Logger;

@Entity
public class Shift {
	
	@Id @GeneratedValue
	private long id;
	@Temporal(TemporalType.TIMESTAMP)
	private Timestamp clockIn;
	@Temporal(TemporalType.TIMESTAMP)
	private Timestamp clockOut;
	private double duration;
	
	public double getDuration() {
		return duration;
	}

	private User user;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Timestamp getClockIn() {
		return clockIn;
	}

	public void setClockIn(Timestamp clockIn) {
		this.clockIn = clockIn;
	}

	public Timestamp getClockOut() {
		return clockOut;
	}

	public void setClockOut(Timestamp clockOut) {
		this.clockOut = clockOut;
		
		long diff = clockOut.getTime() - clockIn.getTime();
		int hours = (int) TimeUnit.MILLISECONDS.toHours(diff);
		int minutes = (int) (TimeUnit.MILLISECONDS.toMinutes(diff) % TimeUnit.HOURS.toMinutes(1));
		double decimal = hours + (minutes/60);
		duration = Math.round(decimal * 100) / 100;
		
	}
	
	public double getDurationFromTime(long ms) {
		long diff = ms - clockIn.getTime();
		int hours = (int) TimeUnit.MILLISECONDS.toHours(diff);
		int minutes = (int) (TimeUnit.MILLISECONDS.toMinutes(diff) % TimeUnit.HOURS.toMinutes(1));
		Logger.info("Hours: {}, Minutes: {}", hours, minutes);
		double minDecimal = (minutes/60.0);
		Logger.info("Decimal minutes: {}", minDecimal);
		double decimalTime = hours + minDecimal;
		Logger.info("Decimal time: {}", decimalTime);
		return Math.round(decimalTime * 100.0) / 100.0;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	

	
	

}
