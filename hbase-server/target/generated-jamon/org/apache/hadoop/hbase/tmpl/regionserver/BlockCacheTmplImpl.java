// Autogenerated Jamon implementation
// /home/hbase/hbase-0.98.8/hbase-server/src/main/jamon/org/apache/hadoop/hbase/tmpl/regionserver/BlockCacheTmpl.jamon

package org.apache.hadoop.hbase.tmpl.regionserver;

// 35, 1
import java.util.Map;
// 36, 1
import org.apache.hadoop.hbase.io.hfile.BlockCacheUtil.CachedBlocksByFile;
// 37, 1
import org.apache.hadoop.hbase.io.hfile.BlockCacheUtil.AgeSnapshot;
// 38, 1
import org.apache.hadoop.hbase.io.hfile.CachedBlock;
// 39, 1
import org.apache.hadoop.conf.Configuration;
// 40, 1
import org.apache.hadoop.hbase.io.hfile.CacheConfig;
// 41, 1
import org.apache.hadoop.hbase.io.hfile.BlockCache;
// 42, 1
import org.apache.hadoop.hbase.io.hfile.bucket.BucketCacheStats;
// 43, 1
import org.apache.hadoop.hbase.io.hfile.bucket.BucketCache;
// 44, 1
import org.apache.hadoop.hbase.io.hfile.bucket.BucketAllocator;
// 45, 1
import org.apache.hadoop.hbase.io.hfile.bucket.BucketAllocator.Bucket;
// 46, 1
import org.apache.hadoop.hbase.io.hfile.slab.SlabCache;
// 47, 1
import org.apache.hadoop.hbase.io.hfile.slab.SingleSizeCache;
// 48, 1
import org.apache.hadoop.util.StringUtils;

public class BlockCacheTmplImpl
  extends org.jamon.AbstractTemplateImpl
  implements org.apache.hadoop.hbase.tmpl.regionserver.BlockCacheTmpl.Intf

{
  private final CacheConfig cacheConfig;
  private final Configuration config;
  protected static org.apache.hadoop.hbase.tmpl.regionserver.BlockCacheTmpl.ImplData __jamon_setOptionalArguments(org.apache.hadoop.hbase.tmpl.regionserver.BlockCacheTmpl.ImplData p_implData)
  {
    return p_implData;
  }
  public BlockCacheTmplImpl(org.jamon.TemplateManager p_templateManager, org.apache.hadoop.hbase.tmpl.regionserver.BlockCacheTmpl.ImplData p_implData)
  {
    super(p_templateManager, __jamon_setOptionalArguments(p_implData));
    cacheConfig = p_implData.getCacheConfig();
    config = p_implData.getConfig();
  }
  
  public void renderNoFlush(@SuppressWarnings({"unused","hiding"}) final java.io.Writer jamonWriter)
    throws java.io.IOException
  {
    // 24, 1
    
  BlockCache bc = cacheConfig == null? null: cacheConfig.getBlockCache();
  String bcUrl = null;
  String bcName = null;
  if (bc != null) {
    bcUrl = "http://hbase.apache.org/devapidocs/" + bc.getClass().getName().replaceAll("\\.", "/") + ".html";
    bcName = bc.getClass().getSimpleName();
  }
  BlockCache [] bcs = cacheConfig == null? null: cacheConfig.getBlockCache() == null? null: cacheConfig.getBlockCache().getBlockCaches();

    // 50, 1
    jamonWriter.write("<div class=\"tabbable\">\n    <ul class=\"nav nav-pills\">\n        <li class=\"active\"><a href=\"#tab_bc_baseInfo\" data-toggle=\"tab\">Base Info</a></li>\n        <li class=\"\"><a href=\"#tab_bc_config\" data-toggle=\"tab\">Config</a></li>\n        <li class=\"\"><a href=\"#tab_bc_stats\" data-toggle=\"tab\">Stats</a></li>\n        <li class=\"\"><a href=\"#tab_bc_l1\" data-toggle=\"tab\">L1</a></li>\n        <li class=\"\"><a href=\"#tab_bc_l2\" data-toggle=\"tab\">L2</a></li>\n    </ul>\n    <div class=\"tab-content\" style=\"padding-bottom: 9px; border-bottom: 1px solid #ddd;\">\n        <div class=\"tab-pane active\" id=\"tab_bc_baseInfo\">\n            ");
    // 60, 13
    {
      // 60, 13
      __jamon_innerUnit__bc_baseInfo(jamonWriter, cacheConfig, bcUrl, bcName);
    }
    // 60, 91
    jamonWriter.write("\n        </div>\n        <div class=\"tab-pane\" id=\"tab_bc_config\">\n            ");
    // 63, 13
    {
      // 63, 13
      __jamon_innerUnit__bc_config(jamonWriter, cacheConfig );
    }
    // 63, 55
    jamonWriter.write("\n        </div>\n        <div class=\"tab-pane\" id=\"tab_bc_stats\">\n            ");
    // 66, 13
    {
      // 66, 13
      __jamon_innerUnit__bc_stats(jamonWriter, cacheConfig );
    }
    // 66, 54
    jamonWriter.write("\n        </div>\n        <div class=\"tab-pane\" id=\"tab_bc_l1\">\n            ");
    // 69, 13
    {
      // 69, 13
      __jamon_innerUnit__bc_l(jamonWriter, bcs == null? bc: bcs[0], "L1" );
    }
    // 69, 66
    jamonWriter.write("\n        </div>\n        <div class=\"tab-pane\" id=\"tab_bc_l2\">\n            ");
    // 72, 13
    {
      // 72, 13
      __jamon_innerUnit__bc_l(jamonWriter, bcs == null? null: bcs.length <= 1? null: bcs[1], "L2"  );
    }
    // 72, 92
    jamonWriter.write("\n        </div>\n    </div>\n</div>\n\n");
  }
  
  
  // 173, 1
  private void __jamon_innerUnit__bc_stats(@SuppressWarnings({"unused","hiding"}) final java.io.Writer jamonWriter, final CacheConfig cacheConfig)
    throws java.io.IOException
  {
    // 177, 1
    if (cacheConfig == null )
    {
      // 177, 28
      jamonWriter.write("\n<p>CacheConfig is null</p>\n");
    }
    // 179, 1
    else
    {
      // 179, 8
      jamonWriter.write("\n<table class=\"table table-striped\">\n    <tr>\n        <th>Attribute</th>\n        <th>Value</th>\n        <th>Description</th>\n    </tr>\n    <tr>\n        <td>Size</td>\n        <td>");
      // 188, 13
      org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(StringUtils.humanReadableInt(cacheConfig.getBlockCache().size())), jamonWriter);
      // 188, 83
      jamonWriter.write("</td>\n        <td>Total size of Block Cache (bytes)</td>\n    </tr>\n    <tr>\n        <td>Free</td>\n        <td>");
      // 193, 13
      org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(StringUtils.humanReadableInt(cacheConfig.getBlockCache().getFreeSize())), jamonWriter);
      // 193, 90
      jamonWriter.write("</td>\n        <td>Free space in Block Cache (bytes)</td>\n    </tr>\n    <tr>\n        <td>Count</td>\n        <td>");
      // 198, 13
      org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(String.format("%,d", cacheConfig.getBlockCache().getBlockCount())), jamonWriter);
      // 198, 84
      jamonWriter.write("</td>\n        <td>Number of blocks in Block Cache</td>\n    </tr>\n    <tr>\n        <td>Evicted</td>\n        <td>");
      // 203, 13
      org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(String.format("%,d", cacheConfig.getBlockCache().getStats().getEvictedCount())), jamonWriter);
      // 203, 97
      jamonWriter.write("</td>\n        <td>Number of blocks evicted</td>\n    </tr>\n    <tr>\n        <td>Evictions</td>\n        <td>");
      // 208, 13
      org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(String.format("%,d", cacheConfig.getBlockCache().getStats().getEvictionCount())), jamonWriter);
      // 208, 98
      jamonWriter.write("</td>\n        <td>Number of times an eviction occurred</td>\n    </tr>\n    <tr>\n        <td>Hits</td>\n        <td>");
      // 213, 13
      org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(String.format("%,d", cacheConfig.getBlockCache().getStats().getHitCount())), jamonWriter);
      // 213, 93
      jamonWriter.write("</td>\n        <td>Number requests that were cache hits</td>\n    </tr>\n    <tr>\n        <td>Hits Caching</td>\n        <td>");
      // 218, 13
      org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(String.format("%,d", cacheConfig.getBlockCache().getStats().getHitCachingCount())), jamonWriter);
      // 218, 100
      jamonWriter.write("</td>\n        <td>Cache hit block requests but only requests set to use Block Cache</td>\n    </tr>\n    <tr>\n        <td>Misses</td>\n        <td>");
      // 223, 13
      org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(String.format("%,d", cacheConfig.getBlockCache().getStats().getMissCount())), jamonWriter);
      // 223, 94
      jamonWriter.write("</td>\n        <td>Number of requests that were cache misses</td>\n    </tr>\n    <tr>\n        <td>Misses Caching</td>\n        <td>");
      // 228, 13
      org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(String.format("%,d", cacheConfig.getBlockCache().getStats().getMissCount())), jamonWriter);
      // 228, 94
      jamonWriter.write("</td>\n        <td>Block requests that were cache misses but only requests set to use Block Cache</td>\n    </tr>\n    <tr>\n        <td>Hit Ratio</td>\n        <td>");
      // 233, 13
      org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(String.format("%,.2f", cacheConfig.getBlockCache().getStats().getHitRatio() * 100)), jamonWriter);
      // 233, 101
      org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf("%"), jamonWriter);
      // 233, 110
      jamonWriter.write("</td>\n        <td>Hit Count divided by total requests count</td>\n    </tr>\n</table>\n<p>If Block Cache is made up of more than one cache -- i.e. a L1 and a L2 -- then the above\nare combined counts. Request count is sum of hits and misses.</p>\n");
    }
    // 239, 7
    jamonWriter.write("\n");
  }
  
  
  // 119, 1
  private void __jamon_innerUnit__bc_config(@SuppressWarnings({"unused","hiding"}) final java.io.Writer jamonWriter, final CacheConfig cacheConfig)
    throws java.io.IOException
  {
    // 123, 1
    if (cacheConfig == null )
    {
      // 123, 28
      jamonWriter.write("\n<p>CacheConfig is null</p>\n");
    }
    // 125, 1
    else
    {
      // 125, 8
      jamonWriter.write("\n<table class=\"table table-striped\">\n    <tr>\n        <th>Attribute</th>\n        <th>Value</th>\n        <th>Description</th>\n    </tr>\n    <tr>\n        <td>Cache DATA on Read</td>\n        <td>");
      // 134, 13
      org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(cacheConfig.shouldCacheDataOnRead()), jamonWriter);
      // 134, 54
      jamonWriter.write("</td>\n        <td>True if DATA blocks are cached on read\n        (INDEX & BLOOM blocks are always cached)</td>\n    </tr>\n    <tr>\n        <td>Cache DATA on Write</td>\n        <td>");
      // 140, 13
      org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(cacheConfig.shouldCacheDataOnWrite()), jamonWriter);
      // 140, 55
      jamonWriter.write("</td>\n        <td>True if DATA blocks are cached on write.</td>\n    </tr>\n    <tr>\n        <td>Cache INDEX on Write</td>\n        <td>");
      // 145, 13
      org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(cacheConfig.shouldCacheIndexesOnWrite()), jamonWriter);
      // 145, 58
      jamonWriter.write("</td>\n        <td>True if INDEX blocks are cached on write</td>\n    </tr>\n    <tr>\n        <td>Cache BLOOM on Write</td>\n        <td>");
      // 150, 13
      org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(cacheConfig.shouldCacheBloomsOnWrite()), jamonWriter);
      // 150, 57
      jamonWriter.write("</td>\n        <td>True if BLOOM blocks are cached on write</td>\n    </tr>\n    <tr>\n        <td>Evict blocks on Close</td>\n        <td>");
      // 155, 13
      org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(cacheConfig.shouldEvictOnClose()), jamonWriter);
      // 155, 51
      jamonWriter.write("</td>\n        <td>True if blocks are evicted from cache when an HFile\n        reader is closed</td>\n    </tr>\n    <tr>\n        <td>Cache DATA in compressed format</td>\n        <td>");
      // 161, 13
      org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(cacheConfig.shouldCacheDataCompressed()), jamonWriter);
      // 161, 58
      jamonWriter.write("</td>\n        <td>True if DATA blocks are cached in their compressed form</td>\n    </tr>\n    <tr>\n        <td>Prefetch on Open</td>\n        <td>");
      // 166, 13
      org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(cacheConfig.shouldPrefetchOnOpen()), jamonWriter);
      // 166, 53
      jamonWriter.write("</td>\n        <td>True if blocks are prefetched into cache on open</td>\n    </tr>\n</table>\n");
    }
    // 170, 7
    jamonWriter.write("\n");
  }
  
  
  // 77, 1
  private void __jamon_innerUnit__bc_baseInfo(@SuppressWarnings({"unused","hiding"}) final java.io.Writer jamonWriter, final CacheConfig cacheConfig, final String bcUrl, final String bcName)
    throws java.io.IOException
  {
    // 83, 1
    
  BlockCache bc = cacheConfig == null? null: cacheConfig.getBlockCache();
  BlockCache [] bcs = bc == null? null: bc.getBlockCaches();
  String bcl1Url = null;
  String bcl1Name = null;
  String bcl2Url = null;
  String bcl2Name = null;
  if (bcs != null) {
    BlockCache bcl1 = bcs[0];
    if (bcl1 != null) {
      bcl1Url = "http://hbase.apache.org/devapidocs/" + bcl1.getClass().getName().replaceAll("\\.", "/") + ".html";
      bcl1Name = bcl1.getClass().getSimpleName();
    }
    if (bcs.length == 2) {
      BlockCache bcl2 = bcs[1];
      bcl2Url = "http://hbase.apache.org/devapidocs/" + bcl2.getClass().getName().replaceAll("\\.", "/") + ".html";
      bcl2Name = bcl2.getClass().getSimpleName();
    }
  }

    // 103, 1
    jamonWriter.write("<table class=\"table table-striped\">\n    <tr>\n        <th>Attribute</th>\n        <th>Value</th>\n        <th>Description</th>\n    </tr>\n    </tr>\n    <tr>\n        <td>Implementation</td>\n        <td><a href=\"");
    // 112, 22
    org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(bcUrl), jamonWriter);
    // 112, 33
    jamonWriter.write("\">");
    // 112, 35
    org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(bcName), jamonWriter);
    // 112, 47
    jamonWriter.write("</a></td>\n        <td>Block Cache implementing class</td>\n    </tr>\n</table>\n<p>See <a href=\"http://hbase.apache.org/book.html#block.cache\">Block Cache</a> in the HBase Reference Guide for help.</p>\n");
  }
  
  
  // 254, 1
  private void __jamon_innerUnit__block_cache(@SuppressWarnings({"unused","hiding"}) final java.io.Writer jamonWriter, final BlockCache bc, final String name)
    throws java.io.IOException
  {
    // 259, 1
    
  final long nanosPerSecond = 1000000000;
  String bcUrl = "http://hbase.apache.org/devapidocs/" + bc.getClass().getName().replaceAll("\\.", "/") + ".html";
  String bcName = bc.getClass().getSimpleName();
  org.apache.hadoop.hbase.io.hfile.BlockCacheUtil.CachedBlocksByFile cbsbf =
    org.apache.hadoop.hbase.io.hfile.BlockCacheUtil.getLoadedCachedBlocksByFile(config, bc);
  AgeSnapshot snapshot = cbsbf.getAgeSnapshot();

  boolean slabCache = bc.getClass().getSimpleName().equals("SlabCache");
  Map<Integer, SingleSizeCache> sizer = null;

  boolean bucketCache = bc.getClass().getSimpleName().equals("BucketCache");
  BucketCacheStats bucketCacheStats = null;
  BucketAllocator bucketAllocator = null;
  Bucket [] buckets = null;

  if (slabCache) {
    sizer = ((SlabCache)bc).getSizer();
  } else if (bucketCache) {
    bucketCacheStats = (BucketCacheStats)bc.getStats();
    bucketAllocator = ((BucketCache)bc).getAllocator();
    buckets = bucketAllocator.getBuckets();
  }

    // 283, 1
    if (cbsbf.isFull() )
    {
      // 283, 23
      jamonWriter.write("\n<p><b>Statistics below is based on sampling first ");
      // 284, 51
      org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(snapshot.getMax()), jamonWriter);
      // 284, 74
      jamonWriter.write(" blocks only</b> (hbase.ui.blockcache.by.file.max)</p> \n");
    }
    // 285, 7
    jamonWriter.write("\n<table id=\"blocks_summary\" class=\"table table-striped\">\n    <tr>\n        <th>Attribute</th>\n        <th>Value</th>\n        <th>Description</th>\n    </tr>\n    <tr>\n        <td>Implementation</td>\n        <td><a href=\"");
    // 294, 22
    org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(bcUrl), jamonWriter);
    // 294, 33
    jamonWriter.write("\">");
    // 294, 35
    org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(bc.getClass().getSimpleName()), jamonWriter);
    // 294, 70
    jamonWriter.write("</a></td>\n        <td>Class implementing this Block Cache Level</td>\n    </tr>\n");
    // 297, 1
    if (bucketCache )
    {
      // 297, 20
      jamonWriter.write("\n    <tr>\n        <td>Implementation</td>\n        <td>");
      // 300, 13
      org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(((BucketCache)bc).getIoEngine()), jamonWriter);
      // 300, 50
      jamonWriter.write("</a></td>\n        <td>IOEngine</td>\n    </tr>\n");
    }
    // 303, 7
    jamonWriter.write("\n    <tr>\n        <td>Count</td>\n        <td>");
    // 306, 13
    org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(String.format("%,d", cbsbf.getCount())), jamonWriter);
    // 306, 57
    jamonWriter.write("</td>\n        <td>Count of Blocks</td>\n    </tr>\n");
    // 309, 1
    if (!bucketCache )
    {
      // 309, 21
      jamonWriter.write("\n    <tr>\n        <td>Count</td>\n        <td>");
      // 312, 13
      org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(String.format("%,d", cbsbf.getDataCount())), jamonWriter);
      // 312, 61
      jamonWriter.write("</td>\n        <td>Count of DATA Blocks</td>\n    </tr>\n");
    }
    // 315, 7
    jamonWriter.write("\n    <tr>\n        <td>Size</td>\n        <td>");
    // 318, 13
    org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(StringUtils.humanReadableInt(cbsbf.getSize())), jamonWriter);
    // 318, 64
    jamonWriter.write("</td>\n        <td>Size of Blocks</td>\n    </tr>\n");
    // 321, 1
    if (!bucketCache )
    {
      // 321, 21
      jamonWriter.write("\n    <tr>\n        <td>Size</td>\n        <td>");
      // 324, 13
      org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(StringUtils.humanReadableInt(cbsbf.getDataSize())), jamonWriter);
      // 324, 68
      jamonWriter.write("</td>\n        <td>Size of DATA Blocks</td>\n    </tr>\n");
    }
    // 327, 7
    jamonWriter.write("\n    <tr>\n        <td>Evicted</td>\n        <td>");
    // 330, 13
    org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(String.format("%,d", bc.getStats().getEvictedCount())), jamonWriter);
    // 330, 72
    jamonWriter.write("</td>\n        <td>The total number of blocks evicted</td>\n    </tr>\n    <tr>\n        <td>Evictions</td>\n        <td>");
    // 335, 13
    org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(String.format("%,d", bc.getStats().getEvictionCount())), jamonWriter);
    // 335, 73
    jamonWriter.write("</td>\n        <td>The total number of times an eviction has occurred</td>\n    </tr>\n");
    // 339, 1
    if (!slabCache )
    {
      // 339, 19
      jamonWriter.write("\n    <tr>\n        <td>Mean</td>\n        <td>");
      // 342, 13
      org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(String.format("%,d", (long)(snapshot.getMean()/nanosPerSecond))), jamonWriter);
      // 342, 82
      jamonWriter.write("</td>\n        <td>Mean age of Blocks in cache (seconds)</td>\n    </tr>\n    <tr>\n        <td>StdDev</td>\n        <td>");
      // 347, 13
      org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(String.format("%,d", (long)(snapshot.getStdDev()/nanosPerSecond))), jamonWriter);
      // 347, 84
      jamonWriter.write("</td>\n        <td>Age standard deviation of Blocks in cache</td>\n    </tr>\n    <tr>\n        <td>Min</td>\n        <td>");
      // 352, 13
      org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(String.format("%,d", (long)(snapshot.getMin()/nanosPerSecond))), jamonWriter);
      // 352, 81
      jamonWriter.write("</td>\n        <td>Min age of Blocks in cache (seconds)</td>\n    </tr>\n    <tr>\n        <td>Max</td>\n        <td>");
      // 357, 13
      org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(String.format("%,d", (long)(snapshot.getMax()/nanosPerSecond))), jamonWriter);
      // 357, 81
      jamonWriter.write("</td>\n        <td>Max age of Blocks in cache (seconds)</td>\n    </tr>\n    <tr>\n        <td>95th Percentile</td>\n        <td>");
      // 362, 13
      org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(String.format("%,d", (long)(snapshot.get95thPercentile()/nanosPerSecond))), jamonWriter);
      // 362, 92
      jamonWriter.write("</td>\n        <td>95th percentile of age of Blocks in cache (seconds)</td>\n    </tr>\n    <tr>\n        <td>99th Percentile</td>\n        <td>");
      // 367, 13
      org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(String.format("%,d", (long)(snapshot.get99thPercentile()/nanosPerSecond))), jamonWriter);
      // 367, 92
      jamonWriter.write("</td>\n        <td>99th percentile of age of Blocks in cache (seconds)</td>\n    </tr>\n");
    }
    // 370, 7
    jamonWriter.write("\n");
    // 371, 1
    if (bucketCache )
    {
      // 371, 20
      jamonWriter.write("\n    <tr>\n        <td>Hits per Second</td>\n        <td>");
      // 374, 13
      org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(bucketCacheStats.getIOHitsPerSecond()), jamonWriter);
      // 374, 56
      jamonWriter.write("</td>\n        <td>Block gets against this cache per second</td>\n    </tr>\n    <tr>\n        <td>Time per Hit</td>\n        <td>");
      // 379, 13
      org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(bucketCacheStats.getIOTimePerHit()), jamonWriter);
      // 379, 53
      jamonWriter.write("</td>\n        <td>Time per cache hit</td>\n    </tr>\n");
    }
    // 382, 7
    jamonWriter.write("\n</table>\n<p>View Block Cache <a href=\"?format=json&bcn=");
    // 385, 47
    org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(name), jamonWriter);
    // 385, 57
    jamonWriter.write("\">as JSON</a> | Block Cache <a href=\"?format=json&bcn=");
    // 385, 111
    org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(name), jamonWriter);
    // 385, 121
    jamonWriter.write("&bcv=file\">as JSON by file</a></p>\n");
    // 386, 1
    if (bucketCache )
    {
      // 386, 20
      jamonWriter.write("\n<p>BucketCache does not discern between DATA and META blocks so we do not show DATA counts (If deploy is using CombinedBlockCache, BucketCache is only DATA blocks</p>\n<h3>BucketCache Buckets</h3>\n<table class=\"table table-striped\">\n    <tr>\n        <th>Bucket Offset</th>\n        <th>Allocation Size</th>\n        <th>Free Count</th>\n        <th>Used Count</th>\n    </tr>\n");
      // 396, 1
      for (Bucket bucket: buckets )
      {
        // 396, 32
        jamonWriter.write("\n    <tr>\n        <td>");
        // 398, 13
        org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(bucket.getBaseOffset()), jamonWriter);
        // 398, 41
        jamonWriter.write("</td>\n        <td>");
        // 399, 13
        org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(bucket.getItemAllocationSize()), jamonWriter);
        // 399, 49
        jamonWriter.write("</td>\n        <td>");
        // 400, 13
        org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(bucket.getFreeBytes()), jamonWriter);
        // 400, 40
        jamonWriter.write("</td>\n        <td>");
        // 401, 13
        org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(bucket.getUsedBytes()), jamonWriter);
        // 401, 40
        jamonWriter.write("</td>\n    </tr>\n");
      }
      // 403, 8
      jamonWriter.write("\n</table>\n");
    }
    // 405, 1
    else if (slabCache )
    {
      // 405, 22
      jamonWriter.write("\n<p>SlabCache does not keep account of block ages so can not show stats on how long blocks have been cached.</p>\n<h3>SlabCache Slabs</h3>\n<table class=\"table table-striped\">\n    <tr>\n        <th>Block Size</th>\n        <th>Size</th>\n        <th>Free Size</th>\n        <th>Count</th>\n        <th>Evicted</th>\n        <th>Evictions</th>\n        <th>Hits</th>\n        <th>Caching</th>\n        <th>Misses</th>\n        <th>Caching</th>\n        <th>Hit Ratio</th>\n    </tr>\n");
      // 422, 1
      for (Map.Entry<Integer, SingleSizeCache> e: sizer.entrySet() )
      {
        // 422, 65
        jamonWriter.write("\n    <tr>\n        <td>");
        // 424, 13
        org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(StringUtils.humanReadableInt(e.getKey())), jamonWriter);
        // 424, 59
        jamonWriter.write("</td>\n        <td>");
        // 425, 13
        org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(StringUtils.humanReadableInt(e.getValue().size())), jamonWriter);
        // 425, 68
        jamonWriter.write("</td>\n        <td>");
        // 426, 13
        org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(StringUtils.humanReadableInt(e.getValue().getFreeSize())), jamonWriter);
        // 426, 75
        jamonWriter.write("</td>\n        <td>");
        // 427, 13
        org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(StringUtils.humanReadableInt(e.getValue().getBlockCount())), jamonWriter);
        // 427, 77
        jamonWriter.write("</td>\n        <td>");
        // 428, 13
        org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(StringUtils.humanReadableInt(e.getValue().getStats().getEvictedCount())), jamonWriter);
        // 428, 90
        jamonWriter.write("</td>\n        <td>");
        // 429, 13
        org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(StringUtils.humanReadableInt(e.getValue().getStats().getEvictionCount())), jamonWriter);
        // 429, 91
        jamonWriter.write("</td>\n        <td>");
        // 430, 13
        org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(StringUtils.humanReadableInt(e.getValue().getStats().getHitCount())), jamonWriter);
        // 430, 86
        jamonWriter.write("</td>\n        <td>");
        // 431, 13
        org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(StringUtils.humanReadableInt(e.getValue().getStats().getHitCachingCount())), jamonWriter);
        // 431, 93
        jamonWriter.write("</td>\n        <td>");
        // 432, 13
        org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(StringUtils.humanReadableInt(e.getValue().getStats().getMissCount())), jamonWriter);
        // 432, 87
        jamonWriter.write("</td>\n        <td>");
        // 433, 13
        org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(StringUtils.humanReadableInt(e.getValue().getStats().getMissCachingCount())), jamonWriter);
        // 433, 94
        jamonWriter.write("</td>\n        <td>");
        // 434, 13
        org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(String.format("%,.2f", e.getValue().getStats().getHitRatio() * 100)), jamonWriter);
        // 434, 86
        org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf("%"), jamonWriter);
        // 434, 95
        jamonWriter.write("</td>\n    </tr>\n");
      }
      // 436, 8
      jamonWriter.write("\n</table>\n");
    }
    // 438, 7
    jamonWriter.write("\n");
    // 439, 1
    
cbsbf = null;

  }
  
  
  // 242, 1
  private void __jamon_innerUnit__bc_l(@SuppressWarnings({"unused","hiding"}) final java.io.Writer jamonWriter, final BlockCache bc, final String name)
    throws java.io.IOException
  {
    // 247, 1
    if (bc == null )
    {
      // 247, 19
      jamonWriter.write("\n<p>No ");
      // 248, 7
      org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(name), jamonWriter);
      // 248, 17
      jamonWriter.write(" deployed</p>\n");
    }
    // 249, 1
    else
    {
      // 249, 8
      jamonWriter.write("\n");
      // 250, 1
      {
        // 250, 1
        __jamon_innerUnit__block_cache(jamonWriter, bc, name);
      }
      // 250, 42
      jamonWriter.write("\n");
    }
    // 251, 7
    jamonWriter.write("\n");
  }
  
  
}
