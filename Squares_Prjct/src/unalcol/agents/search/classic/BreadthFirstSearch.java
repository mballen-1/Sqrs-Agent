package unalcol.agents.search.classic;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2007</p>
 *
 * <p>Company: Universidad Nacional de Colombia</p>
 *
 * @author Jonatan Gómez
 * @version 1.0
 */
public class BreadthFirstSearch<T> extends ClassicSearch<T> {
  public BreadthFirstSearch( int _max_depth ) {
    super( _max_depth );
  }

  public void add( ClassicSearchNode<T> child ){
    list.add(child);
  }

}
