package org.risk.model.army;

import java.util.Enumeration;
import java.util.Hashtable;

import org.risk.model.Utility.NegativeValueException;

/**
 * This class sets and gets the concrete Army type
 * 
 * @author Arij
 *  
 */
public class ArmyDetail {

	private Aircraft aircraft;
	private Artillery artillery;
	private Conscript conscript;
	private HeavyPrecisionArtillery heavyPrecisionArtillery;
	private MechanizedInfantry mechanizedInfantry;
	private NuclearMissile nuclearMissile;
	private ProfessionalSoldier professionalSoldier;
	private SpecialOperationsSoldier specialOperationsSoldier;
	private TacticalMissile tacticalMissile;
	private Volunteer volunteer;
	/**
	 * Default Constructor
	 */
	public ArmyDetail() {
	}
	

	/**
	 * Default Constructor Initializes all army types
	 */
	public ArmyDetail(String name) {
		aircraft = new Aircraft(name);
		artillery = new Artillery(name);
		conscript = new Conscript(name);
		heavyPrecisionArtillery = new HeavyPrecisionArtillery(name);
		mechanizedInfantry = new MechanizedInfantry(name);
		nuclearMissile = new NuclearMissile(name);
		professionalSoldier = new ProfessionalSoldier(name);
		specialOperationsSoldier = new SpecialOperationsSoldier(name);
		tacticalMissile = new TacticalMissile(name);
		volunteer = new Volunteer(name);
	}

	/**
	 * This method returns Aircraft object
	 * 
	 * @return Aircraft object
	 */
	public Aircraft getAircraft() {
		return this.aircraft;
	}

	/**
	 * This method sets the Aircraft object
	 * 
	 * @param aircraft
	 *            Aircraft object
	 */
	public void setAircraft(Aircraft aircraft) {
		this.aircraft = aircraft;
	}

	/**
	 * This method returns Artillery object
	 * 
	 * @return Artillery object
	 */
	public Artillery getArtillery() {
		return this.artillery;
	}

	/**
	 * This method sets the Artillery object
	 * 
	 * @param artillery
	 *            Artillery object
	 */
	public void setArtillery(Artillery artillery) {
		this.artillery = artillery;
	}

	/**
	 * This method returns Conscript object
	 * 
	 * @return Conscript object
	 */
	public Conscript getConscript() {
		return this.conscript;
	}

	/**
	 * This method sets the Conscript object
	 * 
	 * @param conscript
	 *            Conscript object
	 */
	public void setConscript(Conscript conscript) {
		this.conscript = conscript;
	}

	/**
	 * This method returns HeavyPrecisionArtillery object
	 * 
	 * @return HeavyPrecisionArtillery object
	 */
	public HeavyPrecisionArtillery getHeavyPrecisionArtillery() {
		return this.heavyPrecisionArtillery;
	}

	/**
	 * This method sets the HeavyPrecisionArtillery object
	 * 
	 * @param heavyPrecisionArtillery
	 *            HeavyPrecisionArtillery object
	 */
	public void setHeavyPrecisionArtillery(
			HeavyPrecisionArtillery heavyPrecisionArtillery) {
		this.heavyPrecisionArtillery = heavyPrecisionArtillery;
	}

	/**
	 * This method returns MechanizedInfantry object
	 * 
	 * @return MechanizedInfantry object
	 */
	public MechanizedInfantry getMechanizedInfantry() {
		return this.mechanizedInfantry;
	}

	/**
	 * This method sets the MechanizedInfantry object
	 * 
	 * @param mechanizedInfantry
	 *            MechanizedInfantry object
	 */
	public void setMechanizedInfantry(MechanizedInfantry mechanizedInfantry) {
		this.mechanizedInfantry = mechanizedInfantry;
	}

	/**
	 * This method returns NuclearMissile object
	 * 
	 * @return NuclearMissile object
	 */
	public NuclearMissile getNuclearMissile() {
		return this.nuclearMissile;
	}

	/**
	 * This method sets the NuclearMissile object
	 * 
	 * @param nuclearMissile
	 *            NuclearMissile object
	 */
	public void setNuclearMissile(NuclearMissile nuclearMissile) {
		this.nuclearMissile = nuclearMissile;
	}

	/**
	 * This method returns ProfessionalSoldier object
	 * 
	 * @return ProfessionalSoldier object
	 */
	public ProfessionalSoldier getProfessionalSoldier() {
		return this.professionalSoldier;
	}

	/**
	 * This method sets the ProfessionalSoldier object
	 * 
	 * @param professionalSoldier
	 *            ProfessionalSoldier object
	 */
	public void setProfessionalSoldier(ProfessionalSoldier professionalSoldier) {
		this.professionalSoldier = professionalSoldier;
	}

	/**
	 * This method returns SpecialOperationsSoldier object
	 * 
	 * @return SpecialOperationsSoldier object
	 */
	public SpecialOperationsSoldier getSpecialOperationsSoldier() {
		return this.specialOperationsSoldier;
	}

	/**
	 * This method sets the SpecialOperationsSoldier object
	 * 
	 * @param specialOperationsSoldier
	 *            SpecialOperationsSoldier object
	 */
	public void setSpecialOperationsSoldier(
			SpecialOperationsSoldier specialOperationsSoldier) {
		this.specialOperationsSoldier = specialOperationsSoldier;
	}

	/**
	 * This method returns TacticalMissile object
	 * 
	 * @return TacticalMissile object
	 */
	public TacticalMissile getTacticalMissile() {
		return this.tacticalMissile;
	}

	/**
	 * This method sets the TacticalMissile object
	 * 
	 * @param tacticalMissile
	 *            TacticalMissile object
	 */
	public void setTacticalMissile(TacticalMissile tacticalMissile) {
		this.tacticalMissile = tacticalMissile;
	}

	/**
	 * This method returns Volunteer object
	 * 
	 * @return Volunteer object
	 */
	public Volunteer getVolunteer() {
		return this.volunteer;
	}

	/**
	 * This method sets the Volunteer object
	 * 
	 * @param volunteer
	 *            Volunteer object
	 */
	public void setVolunteer(Volunteer volunteer) {
		this.volunteer = volunteer;
	}

/*	public Integer getArmyStrength() {
		return (this.getAircraft().armyStrength()
				+ this.getArtillery().armyStrength()
				+ this.getConscript().armyStrength()
				+ this.getHeavyPrecisionArtillery().armyStrength()
				+ this.getMechanizedInfantry().armyStrength()
				+ this.getNuclearMissile().armyStrength()
				+ this.getProfessionalSoldier().armyStrength()
				+ this.getSpecialOperationsSoldier().armyStrength()
				+ this.getTacticalMissile().armyStrength() + this
				.getVolunteer().armyStrength());
	}
*/
	/**
	 * The method returns the number of aircrafts
	 * @param armyDetail Object of ArmyDetail
	 * @return number of aircrafts
	 */
	private int getAircraftCount(ArmyDetail armyDetail) {
		return (armyDetail.getAircraft().armyStrength() / Aircraft.aircraftStrength);
	}

	/**
	 * The method returns the number of artilleries
	 * @param armyDetail Object of ArmyDetail
	 * @return number of artilleries
	 */
	private int getArtilleryCount(ArmyDetail armyDetail) {
		return (armyDetail.getArtillery().armyStrength() / Artillery.artilleryStrength);
	}

	/**
	 * The method returns the number of conscripts
	 * @param armyDetail Object of ArmyDetail
	 * @return number of conscripts
	 */
	private int getConscriptCount(ArmyDetail armyDetail) {
		return (armyDetail.getConscript().armyStrength() / Conscript.conscriptStrength);
	}

	/**
	 * The method returns the number of heavy precision artilleries
	 * @param armyDetail Object of ArmyDetail
	 * @return number of heavy precision artilleries
	 */
	private int getHeavyPrecisionArtilleryCount(ArmyDetail armyDetail) {
		return (armyDetail.getHeavyPrecisionArtillery().armyStrength() / HeavyPrecisionArtillery.heavyPrecisionArtilleryStrength);
	}

	/**
	 * The method returns the number of mechanized infantry  
	 * @param armyDetail Object of ArmyDetail
	 * @return number of mechanized infantry 
	 */
	private int getMechanizedInfantryCount(ArmyDetail armyDetail) {
		return (armyDetail.getMechanizedInfantry().armyStrength() / MechanizedInfantry.mechanizedInfantryStrength);
	}

	/**
	 * The method returns the number of nuclear missiles
	 * @param armyDetail Object of ArmyDetail
	 * @return number of nuclear missiles
	 */
	private int getNuclearMissileCount(ArmyDetail armyDetail) {
		return (armyDetail.getNuclearMissile().armyStrength() / NuclearMissile.nuclearMissileStrength);
	}

	/**
	 * The method returns the number of professional soldiers
	 * @param armyDetail Object of ArmyDetail
	 * @return number of professional soldiers
	 */
	private int getProfessionalSoldierCount(ArmyDetail armyDetail) {
		return (armyDetail.getProfessionalSoldier().armyStrength() / ProfessionalSoldier.professionalSoldierStrength);
	}

	/**
	 * The method returns the number of special operation soldiers
	 * @param armyDetail Object of ArmyDetail
	 * @return number of special operation soldiers
	 */
	private int getSpecialOperationsSoldierCount(ArmyDetail armyDetail) {
		return (armyDetail.getSpecialOperationsSoldier().armyStrength() / SpecialOperationsSoldier.specialOperationsSoldierStrength);
	}

	/**
	 * The method returns the number of tactical missiles
	 * @param armyDetail Object of ArmyDetail
	 * @return number of tactical missiles
	 */
	private int getTacticalMissileCount(ArmyDetail armyDetail) {
		return (armyDetail.getTacticalMissile().armyStrength() / TacticalMissile.tacticalMissileStrength);
	}

	/**
	 * The method returns the number of volunteers 
	 * @param armyDetail Object of ArmyDetail
	 * @return number of volunteers 
	 */
	private int getVolunteerCount(ArmyDetail armyDetail) {
		return (armyDetail.getVolunteer().armyStrength() / Volunteer.volunteerStrength);
	}

	/**
	 * This method returns number of each army type of the state
	 * @param armyDetail Object of ArmyDetail
	 * @return number of each army type of the state
	 */
	public Hashtable<String, Integer> getArmyCount(ArmyDetail armyDetail) {
		Hashtable<String, Integer> armyCount = new Hashtable<String, Integer>();
		if (armyDetail.getAircraftCount(armyDetail) > 0) {
			armyCount.put(Aircraft.armyName, getAircraftCount(armyDetail));
		}
		if (armyDetail.getArtilleryCount(armyDetail) > 0) {
			armyCount.put(Artillery.armyName, getArtilleryCount(armyDetail));
		}
		if (armyDetail.getConscriptCount(armyDetail) > 0) {
			armyCount.put(Conscript.armyName, getConscriptCount(armyDetail));
		}
		if (armyDetail.getHeavyPrecisionArtilleryCount(armyDetail) > 0) {
			armyCount.put(HeavyPrecisionArtillery.armyName,
					getHeavyPrecisionArtilleryCount(armyDetail));
		}
		if (armyDetail.getMechanizedInfantryCount(armyDetail) > 0) {
			armyCount.put(MechanizedInfantry.armyName,
					getMechanizedInfantryCount(armyDetail));
		}
		if (armyDetail.getNuclearMissileCount(armyDetail) > 0) {
			armyCount.put(NuclearMissile.armyName,
					getNuclearMissileCount(armyDetail));
		}
		if (armyDetail.getProfessionalSoldierCount(armyDetail) > 0) {
			armyCount.put(ProfessionalSoldier.armyName,
					getProfessionalSoldierCount(armyDetail));
		}
		if (armyDetail.getSpecialOperationsSoldierCount(armyDetail) > 0) {
			armyCount.put(SpecialOperationsSoldier.armyName,
					getSpecialOperationsSoldierCount(armyDetail));
		}
		if (armyDetail.getTacticalMissileCount(armyDetail) > 0) {
			armyCount.put(TacticalMissile.armyName,
					getTacticalMissileCount(armyDetail));
		}
		if (armyDetail.getVolunteerCount(armyDetail) > 0) {
			armyCount.put(Volunteer.armyName, getVolunteerCount(armyDetail));
		}
		return armyCount;
	}
	/**
	 * Returns army count
	 * @param armyDetail	takes army details object as parameter
	 * @return	is a string value containing state armies
	 */
	public String getArmyCountStr(ArmyDetail armyDetail){
		Hashtable<String, Integer> armyCount = getArmyCount(armyDetail);
		String stateArmies = "";
		if(!armyCount.isEmpty()){
			Enumeration<String> armyNames = armyCount.keys();
			while( armyNames.hasMoreElements() ) {
				String armyName = armyNames.nextElement();
				stateArmies += armyCount.get(armyName).toString() + " " + armyName + " \n";
			}
		}
		else{
			stateArmies = "No Armies";
		}
		return stateArmies;
	}
	
	/**
	 * This method returns total strength of the armies of the state
	 * @param armyDetail Object of ArmyDetail
	 * @return Total strength of the armies 
	 */
	public int getArmyStrength(ArmyDetail armyDetail) {
		Hashtable<String, Integer> armyCount = getArmyCount(armyDetail);
		int armyStrength = 0;
		try{
		if (!armyCount.isEmpty()) {
			if (armyCount.containsKey(Aircraft.armyName)) {
				armyStrength += armyCount.get(Aircraft.armyName)
						* Aircraft.aircraftStrength;
			}
			if (armyCount.containsKey(Artillery.armyName)) {
				armyStrength += armyCount.get(Artillery.armyName)
						* Artillery.artilleryStrength;
			}
			if (armyCount.containsKey(Conscript.armyName)) {
				armyStrength += armyCount.get(Conscript.armyName)
						* Conscript.conscriptStrength;
			}
			if (armyCount.containsKey(HeavyPrecisionArtillery.armyName)) {
				armyStrength += armyCount.get(HeavyPrecisionArtillery.armyName)
						* HeavyPrecisionArtillery.heavyPrecisionArtilleryStrength;
			}
			if (armyCount.containsKey(MechanizedInfantry.armyName)) {
				armyStrength += armyCount.get(MechanizedInfantry.armyName)
						* MechanizedInfantry.mechanizedInfantryStrength;
			}
			if (armyCount.containsKey(NuclearMissile.armyName)) {
				armyStrength += armyCount.get(NuclearMissile.armyName)
						* NuclearMissile.nuclearMissileStrength;
			}
			if (armyCount.containsKey(ProfessionalSoldier.armyName)) {
				armyStrength += armyCount.get(ProfessionalSoldier.armyName)
						* ProfessionalSoldier.professionalSoldierStrength;
			}
			if (armyCount.containsKey(SpecialOperationsSoldier.armyName)) {
				armyStrength += armyCount.get(SpecialOperationsSoldier.armyName)
						* SpecialOperationsSoldier.specialOperationsSoldierStrength;
			}
			if (armyCount.containsKey(TacticalMissile.armyName)) {
				armyStrength += armyCount.get(TacticalMissile.armyName)
						* TacticalMissile.tacticalMissileStrength;
			}
			if (armyCount.containsKey(Volunteer.armyName)) {
				armyStrength += armyCount.get(Volunteer.armyName)
						* Volunteer.volunteerStrength;
			}
			
			if (armyStrength < 0) {
				throw new NegativeValueException();		
			}
		}
		}catch(NegativeValueException e){
			System.out.println(e.getMessage());
			armyStrength = 0;
		}catch(Exception e){
			System.out.println(e.getMessage());
			armyStrength = 0;
		}
		return armyStrength;
	}
}
