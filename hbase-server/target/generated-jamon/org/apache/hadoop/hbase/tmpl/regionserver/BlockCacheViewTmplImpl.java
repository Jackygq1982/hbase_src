// Autogenerated Jamon implementation
// /home/hbase/hbase-0.98.8/hbase-server/src/main/jamon/org/apache/hadoop/hbase/tmpl/regionserver/BlockCacheViewTmpl.jamon

package org.apache.hadoop.hbase.tmpl.regionserver;

// 28, 1
import java.util.*;
// 29, 1
import org.apache.hadoop.conf.Configuration;
// 30, 1
import org.apache.hadoop.hbase.io.hfile.BlockCacheUtil.CachedBlocksByFile;
// 31, 1
import org.apache.hadoop.hbase.io.hfile.BlockCacheUtil;
// 32, 1
import org.apache.hadoop.hbase.io.hfile.CachedBlock;
// 33, 1
import org.apache.hadoop.hbase.io.hfile.CacheConfig;
// 34, 1
import org.apache.hadoop.hbase.io.hfile.BlockCache;
// 35, 1
import org.apache.hadoop.hbase.io.hfile.bucket.BucketCacheStats;
// 36, 1
import org.apache.hadoop.hbase.io.hfile.bucket.BucketCache;
// 37, 1
import org.apache.hadoop.hbase.io.hfile.bucket.BucketAllocator;
// 38, 1
import org.apache.hadoop.hbase.io.hfile.bucket.BucketAllocator.Bucket;
// 39, 1
import org.apache.hadoop.hbase.io.hfile.slab.SlabCache;
// 40, 1
import org.apache.hadoop.hbase.io.hfile.slab.SingleSizeCache;
// 41, 1
import org.apache.hadoop.util.StringUtils;
// 42, 1
import com.yammer.metrics.stats.Snapshot;

public class BlockCacheViewTmplImpl
  extends org.jamon.AbstractTemplateImpl
  implements org.apache.hadoop.hbase.tmpl.regionserver.BlockCacheViewTmpl.Intf

{
  private final CacheConfig cacheConfig;
  private final Configuration conf;
  private final String bcn;
  private final String bcv;
  protected static org.apache.hadoop.hbase.tmpl.regionserver.BlockCacheViewTmpl.ImplData __jamon_setOptionalArguments(org.apache.hadoop.hbase.tmpl.regionserver.BlockCacheViewTmpl.ImplData p_implData)
  {
    return p_implData;
  }
  public BlockCacheViewTmplImpl(org.jamon.TemplateManager p_templateManager, org.apache.hadoop.hbase.tmpl.regionserver.BlockCacheViewTmpl.ImplData p_implData)
  {
    super(p_templateManager, __jamon_setOptionalArguments(p_implData));
    cacheConfig = p_implData.getCacheConfig();
    conf = p_implData.getConf();
    bcn = p_implData.getBcn();
    bcv = p_implData.getBcv();
  }
  
  public void renderNoFlush(@SuppressWarnings({"unused","hiding"}) final java.io.Writer jamonWriter)
    throws java.io.IOException
  {
    // 44, 1
    
  BlockCache bc = cacheConfig.getBlockCache();
  BlockCache [] bcs = bc.getBlockCaches();
  if (bcn.equals("L1")) {
    bc = bcs == null || bcs.length == 0? bc: bcs[0];
  } else {
    if (bcs.length < 2) {
      System.out.println("There is no L2 block cache");
      return;
    }
    bc = bcs[1];
  }
  CachedBlocksByFile cbsbf = BlockCacheUtil.getLoadedCachedBlocksByFile(conf, bc);

    // 58, 1
    if (bcv.equals("file") )
    {
      // 58, 27
      {
        // 58, 27
        __jamon_innerUnit__bc_by_file(jamonWriter, cbsbf);
      }
    }
    // 58, 59
    else
    {
      // 58, 66
      jamonWriter.write("{");
      // 58, 67
      org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(BlockCacheUtil.toJSON(bc)), jamonWriter);
      // 58, 98
      jamonWriter.write(", ");
      // 58, 100
      org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(cbsbf), jamonWriter);
      // 58, 111
      jamonWriter.write(" }");
    }
    // 58, 119
    jamonWriter.write("\n");
    // 59, 1
    
cbsbf = null;

  }
  
  
  // 63, 1
  private void __jamon_innerUnit__bc_by_file(@SuppressWarnings({"unused","hiding"}) final java.io.Writer jamonWriter, final CachedBlocksByFile cbsbf)
    throws java.io.IOException
  {
    // 67, 1
    jamonWriter.write("[");
    // 67, 2
    for (Map.Entry<String, NavigableSet<CachedBlock>> e: cbsbf.getCachedBlockStatsByFile().entrySet() )
    {
      // 67, 103
      org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(BlockCacheUtil.toJSON(e.getKey(), e.getValue())), jamonWriter);
    }
    // 67, 163
    jamonWriter.write("]\n");
  }
  
  
}
