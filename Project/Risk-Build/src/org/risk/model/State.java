package org.risk.model;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Random;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

import org.risk.model.army.Aircraft;
import org.risk.model.army.Army;
import org.risk.model.army.ArmyDetail;
import org.risk.model.army.Artillery;
import org.risk.model.army.Conscript;
import org.risk.model.army.HeavyPrecisionArtillery;
import org.risk.model.army.MechanizedInfantry;
import org.risk.model.army.NuclearMissile;
import org.risk.model.army.ProfessionalSoldier;
import org.risk.model.army.SpecialOperationsSoldier;
import org.risk.model.army.TacticalMissile;
import org.risk.model.army.Volunteer;
import org.risk.model.battle.BattleArmy;

/**
 * This class represents states of the map
 * 
 * @author Kian
 * 
 */
@XmlType
@XmlAccessorType(XmlAccessType.FIELD)
public class State implements Cloneable {
	// fields
	private String stateName;
	private int stateID;
	private int countryID;
	private int continentID;
	private Boolean isCapital;
	@XmlElement
	private Resource resource;
	@XmlElement
	private ArmyDetail armyDetail;
	@XmlElement
	private ArmyDetail casualty;
	private float xPoint;
	private float yPoint;

	// Constructor
	/**
	 * Default Constructor
	 */
	public State() {
		stateName = "unknown";
		stateID = 0;
		countryID = 0;
		continentID = 0;
		isCapital = false;
		resource = new Resource();
		armyDetail = new ArmyDetail(stateName);
		Random randomX = new Random();
		xPoint = randomX.nextInt(1000);
		Random randomY = new Random();
		yPoint = randomY.nextInt(600);

	}

	/**
	 * Constructor with paramemters
	 * 
	 * @param name
	 *            is the name of State
	 * @param stateId
	 *            is the identity number for state object
	 * @param countryId
	 *            is the identity number for Country object
	 * @param continentId
	 *            is the identity number for Continent object
	 * @param capital
	 *            is the boolean value, describes whether this state is capital
	 *            or not
	 * @param links
	 *            is the instance of Link class
	 */
	public State(String name, int stateId, int countryId, int continentId,
			boolean capital, ArrayList<Integer> links, Resource resource) {
		this.stateName = name;
		this.stateID = stateId;
		this.countryID = countryId;
		this.continentID = continentId;
		this.isCapital = capital;
		this.resource = resource;
		Random randomX = new Random();
		xPoint = randomX.nextInt(1000);
		Random randomY = new Random();
		yPoint = randomY.nextInt(600);

	}

	/**
	 * Constructor with paramemters
	 * 
	 * @param name
	 *            is the name of State
	 */
	public State(String name) {
		this.stateName = name;
		stateID = 0;
		countryID = 0;
		continentID = 0;
		isCapital = false;
		resource = new Resource();
		xPoint = 0;
		yPoint = 0;
	}

	// methods
	/**
	 * This method is used to retrieve the name of the state
	 * 
	 * @return Returns name of the state
	 */
	public String getStateName() {
		return stateName;
	}

	/**
	 * This method is used to assign the State name
	 * 
	 * @param stateName
	 *            is the name of State
	 */
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	/**
	 * This method is used to retrieve the State ID
	 * 
	 * @return Identity of the State
	 */
	public int getStateID() {
		return stateID;
	}

	/**
	 * This method is used to set the stateID
	 * 
	 * @param stateId
	 *            is the Identity of the State
	 */
	public void setStateID(int stateId) {
		this.stateID = stateId;
	}

	/**
	 * This method is used to retrieve the countryID
	 * 
	 * @return Id of the Country
	 */
	public int getCountryID() {
		return countryID;
	}

	/**
	 * This method is used to set country's ID
	 * 
	 * @param countryID
	 *            is the identity number of the country object
	 */
	public void setCountryID(int countryID) {
		this.countryID = countryID;
	}

	/**
	 * This method is used to retrieve the continent ID
	 * 
	 * @return Identity of the Continent
	 */
	public int getContinentID() {
		return continentID;
	}

	/**
	 * This method is used to set the continents Id
	 * 
	 * @param continentID
	 *            is the Identity of the Continent
	 */
	public void setContinentID(int continentID) {
		this.continentID = continentID;
	}

	/**
	 * This method is used to retrieve whether the this state is capital or not
	 * 
	 * @return Returns boolean value (true / false) depending upon whether the
	 *         state is capital or not.
	 */
	public Boolean getIsCapital() {
		return isCapital;
	}

	/**
	 * This method is used to set whether this state is capital or not
	 * 
	 * @param isCapital
	 *            is the boolean variable which sets whether the state is
	 *            capital or not
	 */
	public void setIsCapital(Boolean isCapital) {
		this.isCapital = isCapital;
	}

	/**
	 * The method returns the resource from state object.
	 * 
	 * @return : Resource object of the state
	 */
	public Resource getResource() {
		return this.resource;
	}

	/**
	 * The method sets the resource to state object.
	 * 
	 * @param resource
	 *            is the Resource object
	 * 
	 */
	public void setResource(Resource resource) {
		this.resource = resource;
	}

	/**
	 * This method sets the casualty of the army
	 * 
	 * @param casualty	is the instance of ArmyDetail
	 */
	public void setCasualty(ArmyDetail casualty) {
		this.casualty = casualty;
	}

	/**
	 * This method gets the casualty of the army
	 * 
	 * @return Casualty of the army
	 */
	public ArmyDetail getCasualty() {
		return casualty;
	}

	/**
	 * This method sets the details of the armies of the state
	 * 
	 * @param armyDetail
	 *            Details of the armies of the state
	 */
	public void setArmyDetail(ArmyDetail armyDetail) {
		this.armyDetail = armyDetail;
	}

	/**
	 * This method returns ArmyDetail object of the state
	 * 
	 * @return Army object of the state
	 */
	public ArmyDetail getArmyDetail() {
		return armyDetail;
	}

	/**
	 * This method assigns army to the state object
	 * 
	 * @param army
	 *            The army of the state
	 */
	public void addArmyDetail(Army army) {
		if (army.getClass().equals(Aircraft.class)) {
			Aircraft aircraft = new Aircraft(stateName);
			aircraft.setArmyStrength(this.getArmyDetail().getAircraft()
					.armyStrength()
					+ army.armyStrength());
			this.armyDetail.setAircraft(aircraft);
		} else if (army.getClass().equals(Artillery.class)) {
			Artillery artillery = new Artillery(stateName);
			artillery.setArmyStrength(this.getArmyDetail().getArtillery()
					.armyStrength()
					+ army.armyStrength());
			this.armyDetail.setArtillery(artillery);
		} else if (army.getClass().equals(Conscript.class)) {
			Conscript conscript = new Conscript(stateName);
			conscript.setArmyStrength(this.getArmyDetail().getArtillery()
					.armyStrength()
					+ army.armyStrength());
			this.armyDetail.setConscript(conscript);
		} else if (army.getClass().equals(HeavyPrecisionArtillery.class)) {
			HeavyPrecisionArtillery heavyPrecisionArtillery = new HeavyPrecisionArtillery(
					stateName);
			heavyPrecisionArtillery.setArmyStrength(this.getArmyDetail()
					.getHeavyPrecisionArtillery().armyStrength()
					+ army.armyStrength());
			this.armyDetail.setHeavyPrecisionArtillery(heavyPrecisionArtillery);
		} else if (army.getClass().equals(MechanizedInfantry.class)) {
			MechanizedInfantry mechanizedInfantry = new MechanizedInfantry(
					stateName);
			mechanizedInfantry.setArmyStrength(this.getArmyDetail()
					.getMechanizedInfantry().armyStrength()
					+ army.armyStrength());
			this.armyDetail.setMechanizedInfantry(mechanizedInfantry);
		} else if (army.getClass().equals(NuclearMissile.class)) {
			NuclearMissile nuclearMissile = new NuclearMissile(stateName);
			nuclearMissile.setArmyStrength(this.getArmyDetail()
					.getNuclearMissile().armyStrength()
					+ army.armyStrength());
			this.armyDetail.setNuclearMissile(nuclearMissile);
		} else if (army.getClass().equals(ProfessionalSoldier.class)) {
			ProfessionalSoldier professionalSoldier = new ProfessionalSoldier(
					stateName);
			professionalSoldier.setArmyStrength(this.getArmyDetail()
					.getProfessionalSoldier().armyStrength()
					+ army.armyStrength());
			this.armyDetail.setProfessionalSoldier(professionalSoldier);
		} else if (army.getClass().equals(SpecialOperationsSoldier.class)) {
			SpecialOperationsSoldier specialOperationsSoldier = new SpecialOperationsSoldier(
					stateName);
			specialOperationsSoldier.setArmyStrength(this.getArmyDetail()
					.getSpecialOperationsSoldier().armyStrength()
					+ army.armyStrength());
			this.armyDetail
					.setSpecialOperationsSoldier(specialOperationsSoldier);
		} else if (army.getClass().equals(TacticalMissile.class)) {
			TacticalMissile tacticalMissile = new TacticalMissile(stateName);
			tacticalMissile.setArmyStrength(this.getArmyDetail()
					.getTacticalMissile().armyStrength()
					+ army.armyStrength());
			this.armyDetail.setTacticalMissile(tacticalMissile);
		} else if (army.getClass().equals(Volunteer.class)) {
			Volunteer volunteer = new Volunteer(stateName);
			volunteer.setArmyStrength(this.getArmyDetail().getVolunteer()
					.armyStrength()
					+ army.armyStrength());
			this.armyDetail.setVolunteer(volunteer);
		}
	}

	/**
	 * This method assigns army detail to the state object
	 * 
	 * @param armyDetail
	 *            The army detail
	 */
	public void addArmyDetail(ArmyDetail armyDetail) {
		if (armyDetail.getAircraft().armyStrength() > 0) {
			Aircraft aircraft = new Aircraft(stateName);
			aircraft.setArmyStrength(this.getArmyDetail().getAircraft()
					.armyStrength()
					+ armyDetail.getArmyStrength(armyDetail));
			this.armyDetail.setAircraft(aircraft);
		} else if (armyDetail.getArtillery().armyStrength() > 0) {
			Artillery artillery = new Artillery(stateName);
			artillery.setArmyStrength(this.getArmyDetail().getArtillery()
					.armyStrength()
					+ armyDetail.getArmyStrength(armyDetail));
			this.armyDetail.setArtillery(artillery);
		} else if (armyDetail.getConscript().armyStrength() > 0) {
			Conscript conscript = new Conscript(stateName);
			conscript.setArmyStrength(this.getArmyDetail().getArtillery()
					.armyStrength()
					+ armyDetail.getArmyStrength(armyDetail));
			this.armyDetail.setConscript(conscript);
		} else if (armyDetail.getHeavyPrecisionArtillery().armyStrength() > 0) {
			HeavyPrecisionArtillery heavyPrecisionArtillery = new HeavyPrecisionArtillery(
					stateName);
			heavyPrecisionArtillery.setArmyStrength(this.getArmyDetail()
					.getHeavyPrecisionArtillery().armyStrength()
					+ armyDetail.getArmyStrength(armyDetail));
			this.armyDetail.setHeavyPrecisionArtillery(heavyPrecisionArtillery);
		} else if (armyDetail.getMechanizedInfantry().armyStrength() > 0) {
			MechanizedInfantry mechanizedInfantry = new MechanizedInfantry(
					stateName);
			mechanizedInfantry.setArmyStrength(this.getArmyDetail()
					.getMechanizedInfantry().armyStrength()
					+ armyDetail.getArmyStrength(armyDetail));
			this.armyDetail.setMechanizedInfantry(mechanizedInfantry);
		} else if (armyDetail.getNuclearMissile().armyStrength() > 0) {
			NuclearMissile nuclearMissile = new NuclearMissile(stateName);
			nuclearMissile.setArmyStrength(this.getArmyDetail()
					.getNuclearMissile().armyStrength()
					+ armyDetail.getArmyStrength(armyDetail));
			this.armyDetail.setNuclearMissile(nuclearMissile);
		} else if (armyDetail.getProfessionalSoldier().armyStrength() > 0) {
			ProfessionalSoldier professionalSoldier = new ProfessionalSoldier(
					stateName);
			professionalSoldier.setArmyStrength(this.getArmyDetail()
					.getProfessionalSoldier().armyStrength()
					+ armyDetail.getArmyStrength(armyDetail));
			this.armyDetail.setProfessionalSoldier(professionalSoldier);
		} else if (armyDetail.getSpecialOperationsSoldier().armyStrength() > 0) {
			SpecialOperationsSoldier specialOperationsSoldier = new SpecialOperationsSoldier(
					stateName);
			specialOperationsSoldier.setArmyStrength(this.getArmyDetail()
					.getSpecialOperationsSoldier().armyStrength()
					+ armyDetail.getArmyStrength(armyDetail));
			this.armyDetail
					.setSpecialOperationsSoldier(specialOperationsSoldier);
		} else if (armyDetail.getTacticalMissile().armyStrength() > 0) {
			TacticalMissile tacticalMissile = new TacticalMissile(stateName);
			tacticalMissile.setArmyStrength(this.getArmyDetail()
					.getTacticalMissile().armyStrength()
					+ armyDetail.getArmyStrength(armyDetail));
			this.armyDetail.setTacticalMissile(tacticalMissile);
		} else if (armyDetail.getVolunteer().armyStrength() > 0) {
			Volunteer volunteer = new Volunteer(stateName);
			volunteer.setArmyStrength(this.getArmyDetail().getVolunteer()
					.armyStrength()
					+ armyDetail.getArmyStrength(armyDetail));
			this.armyDetail.setVolunteer(volunteer);
		}
	}

	/**
	 * This method subtracts specific units of strength from a state
	 * 
	 * @param state
	 *            The state which its armies are being reduced
	 * @param strengthToBeRemoved
	 *            The strength which should be subtracted from state strength
	 * @return An object of BattleArmy containing the new armies
	 */
	public BattleArmy reduceArmyDetail(State state, int strengthToBeRemoved) {

		ArmyDetail armyDetail = state.getArmyDetail();
		ArrayList<Army> armyDetailByStrengthAscending = getArmyDetailByStrengthAscending(armyDetail);

		ArmyDetail removedArmy = new ArmyDetail(state.getStateName());
		ArmyDetail remainingArmy = new ArmyDetail(state.getStateName());

		BattleArmy changedArmyPositions = new BattleArmy();

		int removedArmyStrength = 0;
		int perUnitArmyStrength = 0;

		for (int i = 0; i < armyDetailByStrengthAscending.size(); i++) {

			removedArmyStrength = removedArmy.getArmyStrength(removedArmy);

			Army army = armyDetailByStrengthAscending.get(i);
			if (removedArmyStrength < strengthToBeRemoved) {

				int armyRemovedFromConcreteArmyType = 0;
				int armyStrength = army.armyStrength();
				int armyRemainingInConcreteType = 0;

				if (army.getClass().equals(Aircraft.class)) {
					Aircraft removedArmyOfAircraft = new Aircraft(
							state.getStateName());
					Aircraft remainingArmyOfAircrft = new Aircraft(
							state.getStateName());

					perUnitArmyStrength = Aircraft.aircraftStrength;
					armyRemovedFromConcreteArmyType = removedArmyStrength(
							perUnitArmyStrength, army, strengthToBeRemoved,
							removedArmyStrength);

					removedArmyOfAircraft
							.setArmyStrength(armyRemovedFromConcreteArmyType);
					removedArmy.setAircraft(removedArmyOfAircraft);

					if (armyStrength > armyRemovedFromConcreteArmyType) {
						armyRemainingInConcreteType = armyStrength
								- armyRemovedFromConcreteArmyType;
						remainingArmyOfAircrft
								.setArmyStrength(armyRemainingInConcreteType);
						remainingArmy.setAircraft(remainingArmyOfAircrft);
					}

				} else if (army.getClass().equals(Artillery.class)) {
					Artillery removedArmyOfArtillery = new Artillery(
							state.getStateName());
					Artillery remainingArmyOfArtillery = new Artillery(
							state.getStateName());

					perUnitArmyStrength = Artillery.artilleryStrength;

					armyRemovedFromConcreteArmyType = removedArmyStrength(
							perUnitArmyStrength, army, strengthToBeRemoved,
							removedArmyStrength);

					removedArmyOfArtillery
							.setArmyStrength(armyRemovedFromConcreteArmyType);
					removedArmy.setArtillery(removedArmyOfArtillery);

					if (armyStrength > armyRemovedFromConcreteArmyType) {
						armyRemainingInConcreteType = armyStrength
								- armyRemovedFromConcreteArmyType;
						remainingArmyOfArtillery
								.setArmyStrength(armyRemainingInConcreteType);
						remainingArmy.setArtillery(remainingArmyOfArtillery);
					}

				} else if (army.getClass().equals(Conscript.class)) {

					Conscript removedArmyOfConscript = new Conscript(
							state.getStateName());
					Conscript remainingArmyOfConscript = new Conscript(
							state.getStateName());

					perUnitArmyStrength = Conscript.conscriptStrength;

					armyRemovedFromConcreteArmyType = removedArmyStrength(
							perUnitArmyStrength, army, strengthToBeRemoved,
							removedArmyStrength);

					removedArmyOfConscript
							.setArmyStrength(armyRemovedFromConcreteArmyType);
					removedArmy.setConscript(removedArmyOfConscript);

					if (armyStrength > armyRemovedFromConcreteArmyType) {
						armyRemainingInConcreteType = armyStrength
								- armyRemovedFromConcreteArmyType;
						remainingArmyOfConscript
								.setArmyStrength(armyRemainingInConcreteType);
						remainingArmy.setConscript(remainingArmyOfConscript);
					}
				} else if (army.getClass()
						.equals(HeavyPrecisionArtillery.class)) {

					HeavyPrecisionArtillery removedArmyOfHeavyPrecisionArtillery = new HeavyPrecisionArtillery(
							state.getStateName());
					HeavyPrecisionArtillery remainingArmyOfHeavyPrecisionArtillery = new HeavyPrecisionArtillery(
							state.getStateName());

					perUnitArmyStrength = HeavyPrecisionArtillery.heavyPrecisionArtilleryStrength;

					armyRemovedFromConcreteArmyType = removedArmyStrength(
							perUnitArmyStrength, army, strengthToBeRemoved,
							removedArmyStrength);

					removedArmyOfHeavyPrecisionArtillery
							.setArmyStrength(armyRemovedFromConcreteArmyType);
					removedArmy
							.setHeavyPrecisionArtillery(removedArmyOfHeavyPrecisionArtillery);

					if (armyStrength > armyRemovedFromConcreteArmyType) {
						armyRemainingInConcreteType = armyStrength
								- armyRemovedFromConcreteArmyType;
						remainingArmyOfHeavyPrecisionArtillery
								.setArmyStrength(armyRemainingInConcreteType);
						remainingArmy
								.setHeavyPrecisionArtillery(remainingArmyOfHeavyPrecisionArtillery);
					}
				} else if (army.getClass().equals(MechanizedInfantry.class)) {

					MechanizedInfantry removedArmyOfMechanizedInfantry = new MechanizedInfantry(
							state.getStateName());
					MechanizedInfantry remainingArmyOfMechanizedInfantry = new MechanizedInfantry(
							state.getStateName());

					perUnitArmyStrength = MechanizedInfantry.mechanizedInfantryStrength;

					armyRemovedFromConcreteArmyType = removedArmyStrength(
							perUnitArmyStrength, army, strengthToBeRemoved,
							removedArmyStrength);

					removedArmyOfMechanizedInfantry
							.setArmyStrength(armyRemovedFromConcreteArmyType);
					removedArmy
							.setMechanizedInfantry(removedArmyOfMechanizedInfantry);

					if (armyStrength > armyRemovedFromConcreteArmyType) {
						armyRemainingInConcreteType = armyStrength
								- armyRemovedFromConcreteArmyType;
						remainingArmyOfMechanizedInfantry
								.setArmyStrength(armyRemainingInConcreteType);
						remainingArmy
								.setMechanizedInfantry(remainingArmyOfMechanizedInfantry);
					}
				} else if (army.getClass().equals(NuclearMissile.class)) {

					NuclearMissile removedArmyOfNuclearMissile = new NuclearMissile(
							state.getStateName());
					NuclearMissile remainingArmyOfNuclearMissile = new NuclearMissile(
							state.getStateName());

					perUnitArmyStrength = NuclearMissile.nuclearMissileStrength;

					armyRemovedFromConcreteArmyType = removedArmyStrength(
							perUnitArmyStrength, army, strengthToBeRemoved,
							removedArmyStrength);

					removedArmyOfNuclearMissile
							.setArmyStrength(armyRemovedFromConcreteArmyType);
					removedArmy.setNuclearMissile(removedArmyOfNuclearMissile);

					if (armyStrength > armyRemovedFromConcreteArmyType) {
						armyRemainingInConcreteType = armyStrength
								- armyRemovedFromConcreteArmyType;
						remainingArmyOfNuclearMissile
								.setArmyStrength(armyRemainingInConcreteType);
						remainingArmy
								.setNuclearMissile(remainingArmyOfNuclearMissile);
					}
				} else if (army.getClass().equals(ProfessionalSoldier.class)) {

					ProfessionalSoldier removedArmyOfProfessionalSoldier = new ProfessionalSoldier(
							state.getStateName());
					ProfessionalSoldier remainingArmyOfProfessionalSoldier = new ProfessionalSoldier(
							state.getStateName());

					perUnitArmyStrength = ProfessionalSoldier.professionalSoldierStrength;

					armyRemovedFromConcreteArmyType = removedArmyStrength(
							perUnitArmyStrength, army, strengthToBeRemoved,
							removedArmyStrength);

					removedArmyOfProfessionalSoldier
							.setArmyStrength(armyRemovedFromConcreteArmyType);
					removedArmy
							.setProfessionalSoldier(removedArmyOfProfessionalSoldier);

					if (armyStrength > armyRemovedFromConcreteArmyType) {
						armyRemainingInConcreteType = armyStrength
								- armyRemovedFromConcreteArmyType;
						remainingArmyOfProfessionalSoldier
								.setArmyStrength(armyRemainingInConcreteType);
						remainingArmy
								.setProfessionalSoldier(remainingArmyOfProfessionalSoldier);
					}
				} else if (army.getClass().equals(
						SpecialOperationsSoldier.class)) {

					SpecialOperationsSoldier removedArmyOfSpecialOperationsSoldier = new SpecialOperationsSoldier(
							state.getStateName());
					SpecialOperationsSoldier remainingArmyOfSpecialOperationsSoldier = new SpecialOperationsSoldier(
							state.getStateName());

					perUnitArmyStrength = SpecialOperationsSoldier.specialOperationsSoldierStrength;

					armyRemovedFromConcreteArmyType = removedArmyStrength(
							perUnitArmyStrength, army, strengthToBeRemoved,
							removedArmyStrength);

					removedArmyOfSpecialOperationsSoldier
							.setArmyStrength(armyRemovedFromConcreteArmyType);
					removedArmy
							.setSpecialOperationsSoldier(removedArmyOfSpecialOperationsSoldier);

					if (armyStrength > armyRemovedFromConcreteArmyType) {
						armyRemainingInConcreteType = armyStrength
								- armyRemovedFromConcreteArmyType;
						remainingArmyOfSpecialOperationsSoldier
								.setArmyStrength(armyRemainingInConcreteType);
						remainingArmy
								.setSpecialOperationsSoldier(remainingArmyOfSpecialOperationsSoldier);
					}
				} else if (army.getClass().equals(TacticalMissile.class)) {

					TacticalMissile removedArmyOfTacticalMissile = new TacticalMissile(
							state.getStateName());
					TacticalMissile remainingArmyOfTacticalMissile = new TacticalMissile(
							state.getStateName());

					perUnitArmyStrength = TacticalMissile.tacticalMissileStrength;

					armyRemovedFromConcreteArmyType = removedArmyStrength(
							perUnitArmyStrength, army, strengthToBeRemoved,
							removedArmyStrength);

					removedArmyOfTacticalMissile
							.setArmyStrength(armyRemovedFromConcreteArmyType);
					removedArmy
							.setTacticalMissile(removedArmyOfTacticalMissile);

					if (armyStrength > armyRemovedFromConcreteArmyType) {
						armyRemainingInConcreteType = armyStrength
								- armyRemovedFromConcreteArmyType;
						remainingArmyOfTacticalMissile
								.setArmyStrength(armyRemainingInConcreteType);
						remainingArmy
								.setTacticalMissile(remainingArmyOfTacticalMissile);
					}
				} else if (army.getClass().equals(Volunteer.class)) {

					Volunteer removedArmyOfVolunteer = new Volunteer(
							state.getStateName());
					Volunteer remainingArmyOfVolunteer = new Volunteer(
							state.getStateName());

					perUnitArmyStrength = Volunteer.volunteerStrength;

					armyRemovedFromConcreteArmyType = removedArmyStrength(
							perUnitArmyStrength, army, strengthToBeRemoved,
							removedArmyStrength);

					removedArmyOfVolunteer
							.setArmyStrength(armyRemovedFromConcreteArmyType);
					removedArmy.setVolunteer(removedArmyOfVolunteer);

					if (armyStrength > armyRemovedFromConcreteArmyType) {
						armyRemainingInConcreteType = armyStrength
								- armyRemovedFromConcreteArmyType;
						remainingArmyOfVolunteer
								.setArmyStrength(armyRemainingInConcreteType);
						remainingArmy.setVolunteer(remainingArmyOfVolunteer);
					}
				}
			} else {
				if (army.getClass().equals(Aircraft.class)) {
					remainingArmy.setAircraft((Aircraft) army);
				} else if (army.getClass().equals(Artillery.class)) {
					remainingArmy.setArtillery((Artillery) army);
				} else if (army.getClass().equals(Conscript.class)) {
					remainingArmy.setConscript((Conscript) army);
				} else if (army.getClass()
						.equals(HeavyPrecisionArtillery.class)) {
					remainingArmy
							.setHeavyPrecisionArtillery((HeavyPrecisionArtillery) army);
				} else if (army.getClass().equals(MechanizedInfantry.class)) {
					remainingArmy
							.setMechanizedInfantry((MechanizedInfantry) army);
				} else if (army.getClass().equals(NuclearMissile.class)) {
					remainingArmy.setNuclearMissile((NuclearMissile) army);
				} else if (army.getClass().equals(ProfessionalSoldier.class)) {
					remainingArmy
							.setProfessionalSoldier((ProfessionalSoldier) army);
				} else if (army.getClass().equals(
						SpecialOperationsSoldier.class)) {
					remainingArmy
							.setSpecialOperationsSoldier((SpecialOperationsSoldier) army);
				} else if (army.getClass().equals(TacticalMissile.class)) {
					remainingArmy.setTacticalMissile((TacticalMissile) army);
				} else if (army.getClass().equals(Volunteer.class)) {
					remainingArmy.setVolunteer((Volunteer) army);
				}
			}
		}
		changedArmyPositions.setStateCasualties(removedArmy);
		changedArmyPositions.setStateRemainingArmy(remainingArmy);
		return changedArmyPositions;
	}
	/**
	 * The method is used to remove the army strength
	 * @param perUnitArmyStrength	int value represents per unit army strength 
	 * @param army	is the instance of Army
	 * @param strengthToBeRemoved	int value represents strength to be removed
	 * @param totalRemovedArmyStrength		int value represents total number of removed army strength 
	 * @return	the number of armies removed
	 */
	private int removedArmyStrength(int perUnitArmyStrength, Army army,
			int strengthToBeRemoved, int totalRemovedArmyStrength) {
		int numberOfArmyUnits = army.armyStrength() / perUnitArmyStrength;
		int removedArmy = 0;
		int extraDamage = 0;
//		int thresholdDamageCriteria = (int) (perUnitArmyStrength / 1.3);
		for (int i = 0; i < numberOfArmyUnits; i++) {
			removedArmy += perUnitArmyStrength;
			totalRemovedArmyStrength += perUnitArmyStrength;
			if (totalRemovedArmyStrength >= strengthToBeRemoved) {
				break;
			}
		}
		extraDamage = removedArmy - strengthToBeRemoved;
//		if (extraDamage != 0 && extraDamage > thresholdDamageCriteria) {
//			removedArmy = removedArmy - perUnitArmyStrength;
//		}
		return removedArmy;
	}
	/**
	 * The method is used to return the army details depending upon the strength
	 * @param armyDetail	is the instance of ArmyDetail
	 * @return	Returns the arraylist of army
	 */
	private ArrayList<Army> getArmyDetailByStrengthAscending(
			ArmyDetail armyDetail) {
		ArrayList<Army> armyDetailByStrengthAscending = new ArrayList<Army>();
		if (armyDetail.getVolunteer().armyStrength() > 0) {
			armyDetailByStrengthAscending.add(armyDetail.getVolunteer());
		}
		if (armyDetail.getConscript().armyStrength() > 0) {
			armyDetailByStrengthAscending.add(armyDetail.getConscript());
		}
		if (armyDetail.getProfessionalSoldier().armyStrength() > 0) {
			armyDetailByStrengthAscending.add(armyDetail
					.getProfessionalSoldier());
		}
		if (armyDetail.getSpecialOperationsSoldier().armyStrength() > 0) {
			armyDetailByStrengthAscending.add(armyDetail
					.getSpecialOperationsSoldier());
		}
		if (armyDetail.getArtillery().armyStrength() > 0) {
			armyDetailByStrengthAscending.add(armyDetail.getArtillery());
		}

		if (armyDetail.getMechanizedInfantry().armyStrength() > 0) {
			armyDetailByStrengthAscending.add(armyDetail
					.getMechanizedInfantry());
		}
		if (armyDetail.getAircraft().armyStrength() > 0) {
			armyDetailByStrengthAscending.add(armyDetail.getAircraft());
		}
		if (armyDetail.getHeavyPrecisionArtillery().armyStrength() > 0) {
			armyDetailByStrengthAscending.add(armyDetail
					.getHeavyPrecisionArtillery());
		}
		if (armyDetail.getTacticalMissile().armyStrength() > 0) {
			armyDetailByStrengthAscending.add(armyDetail.getTacticalMissile());
		}
		if (armyDetail.getNuclearMissile().armyStrength() > 0) {
			armyDetailByStrengthAscending.add(armyDetail.getNuclearMissile());
		}
		return armyDetailByStrengthAscending;
	}
	/**
	 * The method is used to retrieve the target state army
	 * @param targetState	is the target state in the battle
	 * @param strengthForEachBattle		int value represents the strength for each battle
	 * @param numberOfBattles		int value represents the amount of battles
	 * @return		Returns the arraylist of states
	 * @throws CloneNotSupportedException		throws the Clone Not supported Exception
	 */
	public ArrayList<State> getTargetStateBattleArmies(State targetState,
			int strengthForEachBattle, int numberOfBattles)
			throws CloneNotSupportedException {
		ArrayList<State> targetStateBattleArmies = new ArrayList<State>();
		State targetStateReplica = (State) targetState.clone();
		BattleArmy battleArmy = new BattleArmy();
		for (int i = 0; i < numberOfBattles; i++) {
			State targetStateBattleArmy = new State();
			targetStateBattleArmy.stateName = targetState.getStateName();
			battleArmy = targetStateBattleArmy.reduceArmyDetail(
					targetStateReplica, strengthForEachBattle);
			targetStateBattleArmy
					.setArmyDetail(battleArmy.getStateCasualties());
			targetStateReplica
					.setArmyDetail(battleArmy.getStateRemainingArmy());
			targetStateBattleArmies.add(targetStateBattleArmy);
		}
		return targetStateBattleArmies;
	}

	/**
	 * This method returns resource level of the state by sorting through
	 * StateId
	 * 
	 * @param stateId
	 *            is the identity number of the state
	 * @param states
	 *            is the the list state Objects
	 * @return Returns the resource level of the specific state
	 * @throws Exception
	 *             Is used for handling unfavorable conditions
	 */
	public int getResourceLevelByStateId(int stateId, ArrayList<State> states)
			throws Exception {
		int resourseLevel = -1;
		for (int i = 0; i < states.size(); i++) {
			if (stateId == states.get(i).getStateID()) {
				resourseLevel = states.get(i).getResource().resourceLevel();
				break;
			}
		}
		if (resourseLevel == -1) {
			throw new Exception("Resource level of state is not set");
		}
		return resourseLevel;
	}

	/**
	 * This method returns State after sorting through stateId
	 * 
	 * @param stateId
	 *            is the identity number of the state
	 * @param states
	 *            is the list of state objects
	 * @return returns the state object
	 */
	public State getStateByStateId(int stateId, ArrayList<State> states) {
		State state = new State();
		for (int i = 0; i < states.size(); i++) {
			if (stateId == states.get(i).getStateID()) {
				state = states.get(i);
				break;
			}
		}
		return state;
	}

	/**
	 * This method State after sorting ContinentId
	 * 
	 * @param continentId
	 *            is the Identity number of the continent
	 * @param statesList
	 *            is the list of state objects
	 * @return Returns the list of states
	 */
	public ArrayList<State> getStateByContinentId(int continentId,
			ArrayList<State> statesList) {
		ArrayList<State> tempStates = new ArrayList<State>();
		for (State s : statesList) {
			if (continentId == s.getContinentID()) {
				tempStates.add(s);
			}
		}

		return tempStates;
	}

	/**
	 * This method returns State Index after sorting state id
	 * 
	 * @param stateId
	 *            is the identity number of the state
	 * @param states
	 *            is the list of state objects
	 * @return Returns the particular index from the list of array list
	 * @throws Exception
	 *             : is used for handling unfavourable conditions
	 */
	public int getStateIndexByStateId(int stateId, ArrayList<State> states)
			throws Exception {
		int indexToBeReturned = -1;
		for (int i = 0; i < states.size(); i++) {
			if (stateId == states.get(i).getStateID()) {
				indexToBeReturned = i;
				break;
			}
		}
		if (indexToBeReturned == -1) {
			throw new Exception("State Id not in State List");
		}
		return indexToBeReturned;
	}

	/**
	 * This method returns States after sorting through countryId
	 * 
	 * @param countryId
	 *            is the identity number of country
	 * @param states
	 *            is the list of State objects
	 * @return the list of States
	 */
	public ArrayList<State> getStatesByCountryId(int countryId,
			ArrayList<State> states) {
		ArrayList<State> rtnStateArr = new ArrayList<State>();
		for (int i = 0; i < states.size(); i++) {
			if (countryId == states.get(i).getCountryID()) {
				rtnStateArr.add(states.get(i));
			}
		}
		return rtnStateArr;
	}

	/**
	 * This method returns the list of capital states from the list of states
	 * 
	 * @param states
	 *            is the list of states
	 * @return returns the list of states
	 */
	public ArrayList<State> getCapitalStates(ArrayList<State> states) {
		ArrayList<State> rtnStateArr = new ArrayList<State>();
		for (State state : states) {
			if (state.getIsCapital()) {
				rtnStateArr.add(state);
			}
		}
		return rtnStateArr;
	}

	/**
	 * This method returns the X point of the state on the map
	 * 
	 * @return X point of the state on the map
	 */
	public float getxPoint() {
		return xPoint;
	}

	/**
	 * This method sets the X point of the state on the map
	 * 
	 * @param xPoint
	 *            X point of the state on the map
	 */
	public void setxPoint(float xPoint) {
		this.xPoint = xPoint;
	}

	/**
	 * This method returns the Y point of the state on the map
	 * 
	 * @return Y point of the state on the map
	 */
	public float getyPoint() {
		return yPoint;
	}

	/**
	 * This method sets the Y point of the state on the map
	 * 
	 * @param yPoint
	 *            Y point of the state on the map
	 */
	public void setyPoint(float yPoint) {
		this.yPoint = yPoint;
	}

}