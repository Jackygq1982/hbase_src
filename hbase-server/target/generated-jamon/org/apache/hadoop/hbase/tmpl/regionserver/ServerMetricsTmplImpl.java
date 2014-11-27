// Autogenerated Jamon implementation
// /home/hbase/hbase-0.98.8/hbase-server/src/main/jamon/org/apache/hadoop/hbase/tmpl/regionserver/ServerMetricsTmpl.jamon

package org.apache.hadoop.hbase.tmpl.regionserver;

// 23, 1
import java.util.*;
// 24, 1
import org.apache.hadoop.hbase.regionserver.HRegionServer;
// 25, 1
import org.apache.hadoop.hbase.regionserver.MetricsRegionServerWrapper;
// 26, 1
import org.apache.hadoop.hbase.util.Bytes;
// 27, 1
import org.apache.hadoop.hbase.HRegionInfo;
// 28, 1
import org.apache.hadoop.hbase.ServerName;
// 29, 1
import org.apache.hadoop.hbase.HBaseConfiguration;
// 30, 1
import org.apache.hadoop.hbase.protobuf.ProtobufUtil;
// 31, 1
import org.apache.hadoop.hbase.protobuf.generated.AdminProtos.ServerInfo;
// 32, 1
import org.apache.hadoop.hbase.protobuf.generated.ClusterStatusProtos.RegionLoad;
// 33, 1
import org.apache.hadoop.hbase.metrics.histogram.MetricsHistogram;
// 34, 1
import org.apache.hadoop.hbase.util.DirectMemoryUtils;
// 35, 1
import org.apache.hadoop.util.StringUtils;
// 36, 1
import com.yammer.metrics.stats.Snapshot;
// 37, 1
import java.lang.management.ManagementFactory;

public class ServerMetricsTmplImpl
  extends org.jamon.AbstractTemplateImpl
  implements org.apache.hadoop.hbase.tmpl.regionserver.ServerMetricsTmpl.Intf

{
  private final MetricsRegionServerWrapper mWrap;
  protected static org.apache.hadoop.hbase.tmpl.regionserver.ServerMetricsTmpl.ImplData __jamon_setOptionalArguments(org.apache.hadoop.hbase.tmpl.regionserver.ServerMetricsTmpl.ImplData p_implData)
  {
    return p_implData;
  }
  public ServerMetricsTmplImpl(org.jamon.TemplateManager p_templateManager, org.apache.hadoop.hbase.tmpl.regionserver.ServerMetricsTmpl.ImplData p_implData)
  {
    super(p_templateManager, __jamon_setOptionalArguments(p_implData));
    mWrap = p_implData.getMWrap();
  }
  
  public void renderNoFlush(@SuppressWarnings({"unused","hiding"}) final java.io.Writer jamonWriter)
    throws java.io.IOException
  {
    // 39, 1
    jamonWriter.write("<div class=\"tabbable\">\n    <ul class=\"nav nav-pills\">\n        <li class=\"active\"><a href=\"#tab_baseStats\" data-toggle=\"tab\">Base Stats</a></li>\n        <li class=\"\"><a href=\"#tab_memoryStats\" data-toggle=\"tab\">Memory</a></li>\n        <li class=\"\"><a href=\"#tab_requestStats\" data-toggle=\"tab\">Requests</a></li>\n        <li class=\"\"><a href=\"#tab_hlogStats\" data-toggle=\"tab\">hlogs</a></li>\n        <li class=\"\"><a href=\"#tab_storeStats\" data-toggle=\"tab\">Storefiles</a></li>\n        <li class=\"\"><a href=\"#tab_queueStats\" data-toggle=\"tab\">Queues</a></li>\n    </ul>\n    <div class=\"tab-content\" style=\"padding-bottom: 9px; border-bottom: 1px solid #ddd;\">\n        <div class=\"tab-pane active\" id=\"tab_baseStats\">\n            ");
    // 50, 13
    {
      // 50, 13
      __jamon_innerUnit__baseStats(jamonWriter, mWrap );
    }
    // 50, 43
    jamonWriter.write("\n        </div>\n        <div class=\"tab-pane\" id=\"tab_memoryStats\">\n            ");
    // 53, 13
    {
      // 53, 13
      __jamon_innerUnit__memoryStats(jamonWriter, mWrap );
    }
    // 53, 45
    jamonWriter.write("\n        </div>\n        <div class=\"tab-pane\" id=\"tab_requestStats\">\n            ");
    // 56, 13
    {
      // 56, 13
      __jamon_innerUnit__requestStats(jamonWriter, mWrap );
    }
    // 56, 46
    jamonWriter.write("\n        </div>\n        <div class=\"tab-pane\" id=\"tab_hlogStats\">\n            ");
    // 59, 13
    {
      // 59, 13
      __jamon_innerUnit__hlogStats(jamonWriter, mWrap );
    }
    // 59, 43
    jamonWriter.write("\n        </div>\n        <div class=\"tab-pane\" id=\"tab_storeStats\">\n            ");
    // 62, 13
    {
      // 62, 13
      __jamon_innerUnit__storeStats(jamonWriter, mWrap );
    }
    // 62, 44
    jamonWriter.write("\n        </div>\n        <div class=\"tab-pane\" id=\"tab_queueStats\">\n            ");
    // 65, 13
    {
      // 65, 13
      __jamon_innerUnit__queueStats(jamonWriter, mWrap  );
    }
    // 65, 45
    jamonWriter.write("\n        </div>\n    </div>\n</div>\n\n");
  }
  
  
  // 165, 1
  private void __jamon_innerUnit__requestStats(@SuppressWarnings({"unused","hiding"}) final java.io.Writer jamonWriter, final MetricsRegionServerWrapper mWrap)
    throws java.io.IOException
  {
    // 169, 1
    jamonWriter.write("<table class=\"table table-striped\">\n<tr>\n    <th>Request Per Second</th>\n    <th>Read Request Count</th>\n    <th>Write Request Count</th>\n</tr>\n<tr>\n    <td>");
    // 176, 9
    org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(String.format("%.0f", mWrap.getRequestsPerSecond())), jamonWriter);
    // 176, 66
    jamonWriter.write("</td>\n    <td>");
    // 177, 9
    org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(mWrap.getReadRequestsCount()), jamonWriter);
    // 177, 43
    jamonWriter.write("</td>\n    <td>");
    // 178, 9
    org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(mWrap.getWriteRequestsCount()), jamonWriter);
    // 178, 44
    jamonWriter.write("</td>\n</tr>\n</table>\n");
  }
  
  
  // 183, 1
  private void __jamon_innerUnit__queueStats(@SuppressWarnings({"unused","hiding"}) final java.io.Writer jamonWriter, final MetricsRegionServerWrapper mWrap)
    throws java.io.IOException
  {
    // 187, 1
    jamonWriter.write("<table class=\"table table-striped\">\n<tr>\n    <th>Compaction Queue Size</th>\n    <th>Flush Queue Size</th>\n\n</tr>\n<tr>\n    <td>");
    // 194, 9
    org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(mWrap.getCompactionQueueSize()), jamonWriter);
    // 194, 45
    jamonWriter.write("</td>\n    <td>");
    // 195, 9
    org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(mWrap.getFlushQueueSize()), jamonWriter);
    // 195, 40
    jamonWriter.write("</td>\n</tr>\n</table>\n");
  }
  
  
  // 123, 1
  private void __jamon_innerUnit__hlogStats(@SuppressWarnings({"unused","hiding"}) final java.io.Writer jamonWriter, final MetricsRegionServerWrapper mWrap)
    throws java.io.IOException
  {
    // 127, 1
    jamonWriter.write("<table class=\"table table-striped\">\n<tr>\n    <tr>\n        <th>Num. HLog Files</th>\n        <th>Size. HLog Files (bytes)</th>\n    </tr>\n</tr>\n<tr>\n    <td>");
    // 135, 9
    org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(mWrap.getNumHLogFiles()), jamonWriter);
    // 135, 38
    jamonWriter.write("</td>\n    <td>");
    // 136, 9
    org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(mWrap.getHLogFileSize()), jamonWriter);
    // 136, 38
    jamonWriter.write("</td>\n</tr>\n</table>\n");
  }
  
  
  // 91, 1
  private void __jamon_innerUnit__memoryStats(@SuppressWarnings({"unused","hiding"}) final java.io.Writer jamonWriter, final MetricsRegionServerWrapper mWrap)
    throws java.io.IOException
  {
    // 95, 1
    jamonWriter.write("<table class=\"table table-striped\">\n<tr>\n    <tr>\n        <th>Used Heap</th>\n        <th>Max Heap</th>\n        <th>Direct Memory Used</th>\n        <th>Direct Memory Configured</th>\n        <th>Memstore Size</th>\n    </tr>\n</tr>\n<tr>\n    <td>\n        ");
    // 107, 9
    org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(StringUtils.humanReadableInt(ManagementFactory.getMemoryMXBean().getHeapMemoryUsage().getUsed())), jamonWriter);
    // 107, 111
    jamonWriter.write("\n    </td>\n    <td>\n        ");
    // 110, 9
    org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(StringUtils.humanReadableInt(ManagementFactory.getMemoryMXBean().getHeapMemoryUsage().getMax())), jamonWriter);
    // 110, 110
    jamonWriter.write("\n    </td>\n    <td>\n        ");
    // 113, 9
    org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(StringUtils.humanReadableInt(DirectMemoryUtils.getDirectMemoryUsage())), jamonWriter);
    // 113, 85
    jamonWriter.write("\n    </td>\n    <td>\n        ");
    // 116, 9
    org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(StringUtils.humanReadableInt(DirectMemoryUtils.getDirectMemorySize())), jamonWriter);
    // 116, 84
    jamonWriter.write("\n    </td>\n    <td>");
    // 118, 9
    org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(StringUtils.humanReadableInt(mWrap.getMemstoreSize())), jamonWriter);
    // 118, 68
    jamonWriter.write("</td>\n</tr>\n</table>\n");
  }
  
  
  // 70, 1
  private void __jamon_innerUnit__baseStats(@SuppressWarnings({"unused","hiding"}) final java.io.Writer jamonWriter, final MetricsRegionServerWrapper mWrap)
    throws java.io.IOException
  {
    // 74, 1
    jamonWriter.write("<table class=\"table table-striped\">\n    <tr>\n\n        <th>Requests Per Second</th>\n        <th>Num. Regions</th>\n        <th>Block locality</th>\n        <th>Slow HLog Append Count</th>\n    </tr>\n    <tr>\n        <td>");
    // 83, 13
    org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(String.format("%.0f", mWrap.getRequestsPerSecond())), jamonWriter);
    // 83, 70
    jamonWriter.write("</td>\n        <td>");
    // 84, 13
    org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(mWrap.getNumOnlineRegions()), jamonWriter);
    // 84, 46
    jamonWriter.write("</td>\n        <td>");
    // 85, 13
    org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(mWrap.getPercentFileLocal()), jamonWriter);
    // 85, 46
    jamonWriter.write("</td>\n        <td>");
    // 86, 13
    org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(0), jamonWriter);
    // 86, 20
    jamonWriter.write("</td>\n    </tr>\n</table>\n");
  }
  
  
  // 141, 1
  private void __jamon_innerUnit__storeStats(@SuppressWarnings({"unused","hiding"}) final java.io.Writer jamonWriter, final MetricsRegionServerWrapper mWrap)
    throws java.io.IOException
  {
    // 145, 1
    jamonWriter.write("<table class=\"table table-striped\">\n<tr>\n\n    <th>Num. Stores</th>\n    <th>Num. Storefiles</th>\n    <th>Root Index Size (bytes)</th>\n    <th>Index Size (bytes)</th>\n    <th>Bloom Size (bytes)</th>\n</tr>\n<tr>\n    <td>");
    // 155, 9
    org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(StringUtils.humanReadableInt(mWrap.getNumStores())), jamonWriter);
    // 155, 65
    jamonWriter.write("</td>\n    <td>");
    // 156, 9
    org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(StringUtils.humanReadableInt(mWrap.getNumStoreFiles())), jamonWriter);
    // 156, 69
    jamonWriter.write("</td>\n    <td>");
    // 157, 9
    org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(StringUtils.humanReadableInt(mWrap.getStoreFileIndexSize())), jamonWriter);
    // 157, 74
    jamonWriter.write("</td>\n    <td>");
    // 158, 9
    org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(StringUtils.humanReadableInt(mWrap.getTotalStaticIndexSize())), jamonWriter);
    // 158, 76
    jamonWriter.write("</td>\n    <td>");
    // 159, 9
    org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(StringUtils.humanReadableInt(mWrap.getTotalStaticBloomSize())), jamonWriter);
    // 159, 76
    jamonWriter.write("</td>\n</tr>\n</table>\n");
  }
  
  
}
