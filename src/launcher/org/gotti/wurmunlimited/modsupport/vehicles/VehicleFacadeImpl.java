package org.gotti.wurmunlimited.modsupport.vehicles;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.gotti.wurmunlimited.modloader.ReflectionUtil;

import com.wurmonline.server.behaviours.Seat;
import com.wurmonline.server.behaviours.Vehicle;

public class VehicleFacadeImpl implements VehicleFacade {

	private static Method createOnlyPassengerSeats;
	private static Field embarkString;

	static {
		try {
			createOnlyPassengerSeats = ReflectionUtil.getMethod(Vehicle.class, "createOnlyPassengerSeats");
			embarkString = ReflectionUtil.getField(Vehicle.class, "embarkString");
		} catch (NoSuchMethodException | NoSuchFieldException e) {
			throw new RuntimeException(e);
		}
	}

	private Vehicle v;

	public VehicleFacadeImpl(Vehicle v) {
		this.v = v;
	}

	public <T> T callPrivateMethod(Method method, Object... args) {
		try {
			return ReflectionUtil.callPrivateMethod(v, method, args);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
	}

	public <T> void setPrivateField(Field field, T value) {
		try {
			ReflectionUtil.setPrivateField(v, field, value);
		} catch (IllegalAccessException | IllegalArgumentException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void setUnmountable(boolean b) {
		v.setUnmountable(b);
	}

	@Override
	public void createOnlyPassengerSeats(int i) {
		callPrivateMethod(createOnlyPassengerSeats, Integer.valueOf(i));
	}

	@Override
	public void setSeatFightMod(int i, float f, float a) {
		v.setSeatFightMod(i, f, a);
	}

	@Override
	public void setCreature(boolean b) {
		v.creature = b;
	}

	@Override
	public void setEmbarkString(String string) {
		setPrivateField(embarkString, string);
	}

	@Override
	public void setName(String name) {
		v.name = name;
	}

	@Override
	public void setMaxDepth(float f) {
		v.maxDepth = f;
	}

	@Override
	public void setMaxHeightDiff(float f) {
		v.maxHeightDiff = f;
	}

	@Override
	public void setCommandType(byte i) {
		v.commandType = i;
	}

	@Override
	public void addHitchSeats(Seat[] hitches) {
		v.addHitchSeats(hitches);
	}

}