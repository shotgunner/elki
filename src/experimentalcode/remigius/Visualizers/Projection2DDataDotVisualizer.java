package experimentalcode.remigius.Visualizers;

import org.w3c.dom.Element;

import de.lmu.ifi.dbs.elki.data.NumberVector;
import de.lmu.ifi.dbs.elki.database.Database;
import de.lmu.ifi.dbs.elki.math.linearalgebra.Vector;
import de.lmu.ifi.dbs.elki.visualization.VisualizationProjection;
import de.lmu.ifi.dbs.elki.visualization.svg.SVGPlot;
import de.lmu.ifi.dbs.elki.visualization.svg.SVGUtil;

/**
 * Generates a SVG-Element containing "dots" as markers representing the Database's
 * objects. <br>
 * Future versions may contain a parameter for switching the used markers.
 * 
 * @author Remigius Wojdanowski
 * 
  * @param <NV> Type of the DatabaseObject being visualized.
 */
public class Projection2DDataDotVisualizer<NV extends NumberVector<NV, ?>> extends Projection2DVisualizer<NV> {

  /**
   * A short name characterizing this Visualizer.
   */
  private static final String NAME = "Dots";
  
  /**
   * Generic tag to indicate the type of element. Used in IDs, CSS-Classes etc.
   */
  public static final String MARKER = "marker";
  
  /**
   * Initializes this Visualizer.
   * 
   * @param database contains all objects to be processed.
   */
  public void init(VisualizerContext context) {
    super.init(NAME, context);
    super.setLevel(Visualizer.LEVEL_BACKGROUND + 1);
  }

  @Override
  public Element visualize(SVGPlot svgp, VisualizationProjection proj) {
    Element layer = super.setupCanvas(svgp, proj);
    Database<NV> database = context.getDatabase();
    for(int id : database) {
      Vector v = proj.projectDataToRenderSpace(database.get(id));
      Element dot = SVGUtil.svgCircle(svgp.getDocument(), v.get(0), v.get(1), 0.005);
      SVGUtil.addCSSClass(dot, MARKER);
      layer.appendChild(dot);
    }
    return layer;
  }
}
