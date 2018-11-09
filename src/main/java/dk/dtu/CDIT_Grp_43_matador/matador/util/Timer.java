package dk.dtu.CDIT_Grp_43_matador.matador.util;

public class Timer {
	private final double MSPERSEC = 1000;
	private double msPerTick;
	
	private int missingTicks;
	private long last;
	private int tps;
	private long dMs = 0;
	
	public Timer(int tps) {
		this.tps = tps;
	}
	
	public void initTimer() {
		last = System.currentTimeMillis();
		missingTicks = 1;
		msPerTick = MSPERSEC / tps;
		
	}
	
	public void update() {
		long now = System.currentTimeMillis();
		long deltaMs = now - last;
		last = now;
		dMs += deltaMs;
		if (dMs >= msPerTick) {
			missingTicks++;
			dMs -= msPerTick;
		}
	}
	
	public int getMissingTicks() {
		return missingTicks;
	}
	
	public void tick() {
		missingTicks--;
	}
}
