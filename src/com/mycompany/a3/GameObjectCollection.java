package com.mycompany.a3;

import java.util.ArrayList;

/*
 * Purpose of this class is to hold all the game objects instead of having it stored in the 
 * GameWorld Class and also to alter and iterate GameObjects.
 */
public class GameObjectCollection implements ICollection{
//create a Arraylist to store all GameObjects
private ArrayList<GameObject> ObjectCollection;

public GameObjectCollection() {
	ObjectCollection = new ArrayList<GameObject>();
	
}
//Must Include a way to iterator GameObjects
@Override
public IIterator getIterator() {
	return new GameObjectIterator();
	
}
@Override
public void add(GameObject object) {
	ObjectCollection.add(object);
	
}
/*
 * Resets all GameObjects in ObjectCollection
 */
public void clear() {
	ObjectCollection.clear();
}

private class GameObjectIterator implements IIterator{
	private int currentIndex;
	public GameObjectIterator() {
		currentIndex = -1;
	}
	@Override
	public boolean hasNext() {
		//if the collection size is less than or equal to 0
		//then return false
		if(ObjectCollection.size() <=0) 
			return false;
		//if currentIndex is the last index of the ObjectCollection 
		//Then return false
			if(currentIndex == ObjectCollection.size()-1)
			return false;
			//else return true
			return true;
		
	}
	

	@Override
	public GameObject getNext() {
		//adds one to currentIndex to go to the next index
		currentIndex++;
		return ObjectCollection.get(currentIndex);
	}
	@Override
	public void remove(GameObject object) {
		//Removes the last index
		ObjectCollection.remove(object);
		
	}
	
	
}
}