package org.risk.model.battle;

import java.util.ArrayList;

import org.risk.model.Logger;
import org.risk.model.State;
/**
 * The instance of this Class is used to apply multiple battles
 * @author Arij
 *
 */
public class MultipleBattleFronts {
	
	public static ArrayList<String> battleFrontCasualtiesStartup;
	/**
	 * Constructor
	 * @param targetState	is the target state in th battle
	 * @param attackingStates	is the attacking state in the battle
	 * @throws CloneNotSupportedException	throws Clone not support exception
	 * @throws InterruptedException		throws interrupted exception
	 */
	public MultipleBattleFronts(State targetState,
			ArrayList<State> attackingStates) throws CloneNotSupportedException, InterruptedException {
		
		Logger.logMessage("-----------------");
		Logger.logMessage("Attack:");
		Logger.logMessage("Attacking "+targetState.getStateName()+ " with "+ getAttackingStateNames( attackingStates));
		Logger.logMessage("-----------------");
		Logger.logMessage("\n  ");
		
		battleFrontCasualtiesStartup = new ArrayList<String>();
		logArmyDetails(targetState, attackingStates);
		int strengthForEachBattle = targetState.getArmyDetail()
				.getArmyStrength(targetState.getArmyDetail())
				/ attackingStates.size();
		ArrayList<State> targetStateBattleArmies = targetState
				.getTargetStateBattleArmies(targetState, strengthForEachBattle,
						attackingStates.size());
		logBattleFrontArmyDetails(targetStateBattleArmies, attackingStates);
		int numOfThreads = attackingStates.size();
		Thread[] threads = new Thread[numOfThreads];
		for (int i = 0; i < attackingStates.size(); i++) {
			Thread.sleep(500);
			threads[i] = new Thread(new BattleGround(targetStateBattleArmies.get(i),
					attackingStates.get(i)));
			threads[i].start();
		}
		for (int i = 0; i < threads.length; i++) {
		    try {
		       threads[i].join();
		       //Logger.logMessage(i + "thread died");
		       //threads.n
		    } catch (InterruptedException ignore) {}
		}
		//Logger.logMessage("all threads died");
		// targetState.setArmyDetail(TargetStateBattleArmyBuilder.totalArmyDetailOfTargetState.getArmyDetail());
		// System.out.println(BattleGround.battleFrontDetails);
		// System.out.println(BattleGround.battleCasualtyDetails);

		/*
		 * temp code to avoid multithreading int strengthToBeRemoved =
		 * (targetState.getStateArmyStrength()
		 * +attackingStates.get(0).getStateArmyStrength())/25; battleArmyBuilder
		 * = new TargetStateBattleArmyBuilder();
		 * startBattles(targetState,strengthToBeRemoved); battleArmyBuilder =
		 * new AttackingStateBattleArmyBuilder();
		 * startBattles(attackingStates.get(0), strengthToBeRemoved);
		 */
	}
	/**
	 * The method is used to log army details
	 * @param targetState		is the target state in the battle
	 * @param attackingStates	is the attacking state in the battle
	 */	 
	private void logArmyDetails(State targetState,
			ArrayList<State> attackingStates) {
		Logger.logMessage("Pre-Battle Armies");
		Logger.logMessage(targetState.getStateName() + ":");
		Logger.logMessage(targetState.getArmyDetail().getArmyCountStr(
				targetState.getArmyDetail()));
		Logger.logMessage("");
		for (int i = 0; i < attackingStates.size(); i++) {
			Logger.logMessage(attackingStates.get(i).getStateName() + ":");
			Logger.logMessage(attackingStates.get(i).getArmyDetail()
					.getArmyCountStr(attackingStates.get(i).getArmyDetail()));
			Logger.logMessage("");
		}
	}
	/**
	 * The method is used to log the battle front army details
	 * @param targetStateBattleArmies		is the target state in the battle		
	 * @param attackingStates				is the attacking state in the battle
	 */
	private void logBattleFrontArmyDetails(
			ArrayList<State> targetStateBattleArmies,
			ArrayList<State> attackingStates) {
		for (int i = 0; i < attackingStates.size(); i++) {
			Logger.logMessage("Battle front " + targetStateBattleArmies.get(i).getStateName()
					+ " vs " + attackingStates.get(i).getStateName());
			Logger.logMessage(targetStateBattleArmies.get(i).getStateName() + ":");
			Logger.logMessage(targetStateBattleArmies
					.get(i)
					.getArmyDetail()
					.getArmyCountStr(
							targetStateBattleArmies.get(i).getArmyDetail()));
			Logger.logMessage("");
			Logger.logMessage(attackingStates.get(i).getStateName() + ":");
			Logger.logMessage(attackingStates.get(i).getArmyDetail()
					.getArmyCountStr(attackingStates.get(i).getArmyDetail()));
			battleFrontCasualtiesStartup
					.add("Battle front "
							+ targetStateBattleArmies.get(i).getStateName()
							+ " vs "
							+ attackingStates.get(i).getStateName()
							+ ":"
							+ "\n [Random factor + relative strength calculation result]");
		}

	}
	/**
	 * The method returns the attacking state name
	 * @param attackingStates		is the attacking state in the battle
	 * @return		Returns String represents the name of states
	 */
	private String getAttackingStateNames(ArrayList<State> attackingStates){
		String attackingStatesStr = "";
		for(int i = 0; i < attackingStates.size(); i++){
			attackingStatesStr += attackingStates.get(i).getStateName()+", ";
		}
		return attackingStatesStr;
	}

}
