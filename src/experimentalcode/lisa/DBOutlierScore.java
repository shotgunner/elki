package experimentalcode.lisa;

import java.util.HashMap;

import de.lmu.ifi.dbs.elki.data.DatabaseObject;
import de.lmu.ifi.dbs.elki.database.Database;
import de.lmu.ifi.dbs.elki.database.ids.DBID;
import de.lmu.ifi.dbs.elki.distance.distancevalue.Distance;
import de.lmu.ifi.dbs.elki.utilities.optionhandling.parameterization.Parameterization;

/**
  
      // compute percentage of neighbors in the given neighborhood with size d
 * @author Lisa Reichert
 *
 * @param <O>
 * @param <D>
 */
public  class DBOutlierScore<O extends DatabaseObject, D extends Distance<D>> extends AbstractDBOutlierDetection<O,D> {
  public DBOutlierScore(Parameterization config) {
    super(config);
  }

  @Override
  protected HashMap<DBID, Double> computeOutlierScores(Database<O> database, D d) {
    double n;

    HashMap<DBID, Double> scores= new HashMap<DBID, Double>();
    for(DBID id : database) {
      // compute percentage of neighbors in the given neighborhood with size d
      n = (database.rangeQuery(id, d, getDistanceFunction()).size()) / (double) database.size();
      scores.put(id, 1-n);
    }
    scores.toString();
    return scores;
  }
}
