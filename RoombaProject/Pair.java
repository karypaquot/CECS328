
@SuppressWarnings("rawtypes")
public class Pair<T, S> implements Comparable 
{
	public T first;
	public S second;
	public Pair(T f, S s) { 
    first = f; second = s; 
  }
	public Pair() { 
    first=null; second=null; 
  }

  //prints out the pairs
	@Override
	public String toString() { 
    return "(" + first.toString() + ", " + second.toString() + ")"; 
  }


  //returns true if the two pairs are equal, returns false if the two pairs are not equal
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object o) 
  { 
		Pair<T,S> other=(Pair<T,S>)o;
		if (first.getClass().equals(Integer.class)&&second.getClass().equals(Integer.class))
			return ((((Integer)first).intValue()==((Integer)other.first).intValue())&&
					(((Integer)second).intValue()==((Integer)other.second).intValue()));
		return ((this.first.equals(other.first))&&(this.second.equals(other.second))); 
  }

  // Returns a hash code value for the object. 
	@Override
	public int hashCode() 
  {
		if (first.getClass().equals(Integer.class)&&second.getClass().equals(Integer.class))
			return ((Integer)first).intValue()<<16|(((Integer)second).intValue()&32767);
		else if (first.getClass().equals(String.class)&&second.getClass().equals(Integer.class))
			return ((String)first+","+(Integer)second).hashCode();
		else if (first.getClass().equals(String.class)) 
			return ((String)first+","+(String)second).hashCode(); 
		else return first.hashCode()+second.hashCode(); 
  }

  // Compares this object with the specified object for order. Returns a negative integer, zero, or a positive integer as this object is less than, equal to, or greater than the specified object.
	@SuppressWarnings("unchecked")
	@Override
	public int compareTo(Object otherObject) 
  {
		Pair<T,S> other=(Pair<T,S>) otherObject;
		if ((first.getClass().equals(Integer.class))&&(second.getClass().equals(Integer.class)))
			if (((Integer)first).intValue()!=((Integer)other.first).intValue())
				return (((Integer)first).intValue()-((Integer)other.first).intValue());
			else return (((Integer)second).intValue()-((Integer)other.second).intValue());
		throw new RuntimeException("Not implemented.");
	}
  
  // returns first value in pair
  public T getFirst() {
    return first;
  }
  //returns second value in pair
  public S getSecond(){
    return second;
  }
}
