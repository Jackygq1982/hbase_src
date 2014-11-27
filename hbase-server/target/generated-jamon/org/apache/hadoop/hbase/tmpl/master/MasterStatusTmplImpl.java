// Autogenerated Jamon implementation
// /home/hbase/hbase-0.98.8/hbase-server/src/main/jamon/org/apache/hadoop/hbase/tmpl/master/MasterStatusTmpl.jamon

package org.apache.hadoop.hbase.tmpl.master;

// 33, 1
import java.util.*;
// 34, 1
import org.apache.hadoop.util.StringUtils;
// 35, 1
import org.apache.hadoop.hbase.util.Bytes;
// 36, 1
import org.apache.hadoop.hbase.util.JvmVersion;
// 37, 1
import org.apache.hadoop.hbase.util.FSUtils;
// 38, 1
import org.apache.hadoop.hbase.master.HMaster;
// 39, 1
import org.apache.hadoop.hbase.master.AssignmentManager;
// 40, 1
import org.apache.hadoop.hbase.master.ServerManager;
// 41, 1
import org.apache.hadoop.hbase.HConstants;
// 42, 1
import org.apache.hadoop.hbase.NamespaceDescriptor;
// 43, 1
import org.apache.hadoop.hbase.ServerLoad;
// 44, 1
import org.apache.hadoop.hbase.ServerName;
// 45, 1
import org.apache.hadoop.hbase.client.HBaseAdmin;
// 46, 1
import org.apache.hadoop.hbase.client.HConnectionManager;
// 47, 1
import org.apache.hadoop.hbase.HTableDescriptor;
// 48, 1
import org.apache.hadoop.hbase.HBaseConfiguration;
// 49, 1
import org.apache.hadoop.hbase.TableName;
// 50, 1
import org.apache.hadoop.hbase.protobuf.generated.HBaseProtos.SnapshotDescription;
// 51, 1
import org.apache.hadoop.hbase.master.DeadServer;
// 52, 1
import org.apache.hadoop.hbase.protobuf.ProtobufUtil;
// 53, 1
import org.apache.hadoop.hbase.security.visibility.VisibilityConstants;
// 54, 1
import org.apache.hadoop.hbase.security.access.AccessControlLists;

public class MasterStatusTmplImpl
  extends org.jamon.AbstractTemplateImpl
  implements org.apache.hadoop.hbase.tmpl.master.MasterStatusTmpl.Intf

{
  private final HMaster master;
  private final HBaseAdmin admin;
  private final String format;
  private final String filter;
  private final ServerName metaLocation;
  private final boolean catalogJanitorEnabled;
  private final Set<ServerName> deadServers;
  private final Map<String,Integer> frags;
  private final List<ServerName> servers;
  private final AssignmentManager assignmentManager;
  private final ServerManager serverManager;
  // 66, 1
  
  public String formatZKString() {
    StringBuilder quorums = new StringBuilder();
    String zkQuorum = master.getZooKeeperWatcher().getQuorum();

    if (null == zkQuorum) {
      return quorums.toString();
    }

    String[] zks = zkQuorum.split(",");

    if (zks.length == 0) {
      return quorums.toString();
    }

    for(int i = 0; i < zks.length; ++i) {
      quorums.append(zks[i].trim()).append(",");

      if ((i+1) % 4 == 0 && i != (zks.length - 1)) {
        quorums.append("<br/>");
      }
    }

    quorums.setLength(quorums.length() - 1);
    return quorums.toString();
  }

  protected static org.apache.hadoop.hbase.tmpl.master.MasterStatusTmpl.ImplData __jamon_setOptionalArguments(org.apache.hadoop.hbase.tmpl.master.MasterStatusTmpl.ImplData p_implData)
  {
    if(! p_implData.getFormat__IsNotDefault())
    {
      p_implData.setFormat("html");
    }
    if(! p_implData.getFilter__IsNotDefault())
    {
      p_implData.setFilter("general");
    }
    if(! p_implData.getMetaLocation__IsNotDefault())
    {
      p_implData.setMetaLocation(null);
    }
    if(! p_implData.getCatalogJanitorEnabled__IsNotDefault())
    {
      p_implData.setCatalogJanitorEnabled(true);
    }
    if(! p_implData.getDeadServers__IsNotDefault())
    {
      p_implData.setDeadServers(null);
    }
    if(! p_implData.getFrags__IsNotDefault())
    {
      p_implData.setFrags(null);
    }
    if(! p_implData.getServers__IsNotDefault())
    {
      p_implData.setServers(null);
    }
    if(! p_implData.getAssignmentManager__IsNotDefault())
    {
      p_implData.setAssignmentManager(null);
    }
    if(! p_implData.getServerManager__IsNotDefault())
    {
      p_implData.setServerManager(null);
    }
    return p_implData;
  }
  public MasterStatusTmplImpl(org.jamon.TemplateManager p_templateManager, org.apache.hadoop.hbase.tmpl.master.MasterStatusTmpl.ImplData p_implData)
  {
    super(p_templateManager, __jamon_setOptionalArguments(p_implData));
    master = p_implData.getMaster();
    admin = p_implData.getAdmin();
    format = p_implData.getFormat();
    filter = p_implData.getFilter();
    metaLocation = p_implData.getMetaLocation();
    catalogJanitorEnabled = p_implData.getCatalogJanitorEnabled();
    deadServers = p_implData.getDeadServers();
    frags = p_implData.getFrags();
    servers = p_implData.getServers();
    assignmentManager = p_implData.getAssignmentManager();
    serverManager = p_implData.getServerManager();
  }
  
  public void renderNoFlush(@SuppressWarnings({"unused","hiding"}) final java.io.Writer jamonWriter)
    throws java.io.IOException
  {
    // 57, 1
    if (format.equals("json") )
    {
      // 57, 30
      jamonWriter.write("\n  ");
      // 58, 3
      {
        org.apache.hadoop.hbase.tmpl.common.TaskMonitorTmpl __jamon__var_0 = new org.apache.hadoop.hbase.tmpl.common.TaskMonitorTmpl(this.getTemplateManager());
        __jamon__var_0.setFormat("json" );
        __jamon__var_0.setFilter(filter);
        __jamon__var_0.renderNoFlush(jamonWriter);
      }
      // 58, 68
      jamonWriter.write("\n  ");
      // 59, 3
      return; 
    }
    // 60, 7
    jamonWriter.write("\n");
    // 61, 1
    
ServerManager serverManager = master.getServerManager();
AssignmentManager assignmentManager = master.getAssignmentManager();

    // 94, 1
    jamonWriter.write("<!--[if IE]>\n<!DOCTYPE html>\n<![endif]-->\n<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n<html lang=\"en\">\n  <head>\n    <meta charset=\"utf-8\">\n    <title>Master: ");
    // 101, 20
    org.jamon.escaping.Escaping.NONE.write(org.jamon.emit.StandardEmitter.valueOf(master.getServerName().getHostname()), jamonWriter);
    // 101, 62
    jamonWriter.write("</title>\n    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n    <meta name=\"description\" content=\"\">\n    <link href=\"/static/css/bootstrap.min.css\" rel=\"stylesheet\">\n    <link href=\"/static/css/bootstrap-theme.min.css\" rel=\"stylesheet\">\n    <link href=\"/static/css/hbase.css\" rel=\"stylesheet\">\n  </head>\n\n  <body>\n\n    <div class=\"navbar  navbar-fixed-top navbar-default\">\n        <div class=\"container\">\n            <div class=\"navbar-header\">\n                <button type=\"button\" class=\"navbar-toggle\" data-toggle=\"collapse\" data-target=\".navbar-collapse\">\n                    <span class=\"icon-bar\"></span>\n                    <span class=\"icon-bar\"></span>\n                    <span class=\"icon-bar\"></span>\n                </button>\n                <a class=\"navbar-brand\" href=\"/master-status\"><img src=\"/static/hbase_logo_small.png\" alt=\"HBase Logo\"/></a>\n            </div>\n            <div class=\"collapse navbar-collapse\">\n                <ul class=\"nav navbar-nav\">\n                <li class=\"active\"><a href=\"/\">Home</a></li>\n                <li><a href=\"/tablesDetailed.jsp\">Table Details</a></li>\n                <li><a href=\"/logs/\">Local Logs</a></li>\n                <li><a href=\"/logLevel\">Log Level</a></li>\n                <li><a href=\"/dump\">Debug Dump</a></li>\n                <li><a href=\"/jmx\">Metrics Dump</a></li>\n                ");
    // 129, 17
    if (HBaseConfiguration.isShowConfInServlet())
    {
      // 129, 64
      jamonWriter.write("\n                <li><a href=\"/conf\">HBase Configuration</a></li>\n                ");
    }
    // 131, 23
    jamonWriter.write("\n               </ul>\n            </div><!--/.nav-collapse -->\n        </div>\n    </div>\n\n    <div class=\"container\">\n\t");
    // 138, 2
    if (master.isActiveMaster() )
    {
      // 138, 33
      jamonWriter.write("\n        <div class=\"row inner_header\">\n            <div class=\"page-header\">\n                <h1>Master <small>");
      // 141, 35
      org.jamon.escaping.Escaping.NONE.write(org.jamon.emit.StandardEmitter.valueOf(master.getServerName().getHostname()), jamonWriter);
      // 141, 77
      jamonWriter.write(" </small></h1>\n            </div>\n        </div>\n\n        <div class=\"row\">\n        <!-- Various warnings that cluster admins should be aware of -->\n        ");
      // 147, 9
      if (JvmVersion.isBadJvmVersion() )
      {
        // 147, 45
        jamonWriter.write("\n          <div class=\"alert alert-error\">\n          Your current JVM version ");
        // 149, 36
        org.jamon.escaping.Escaping.NONE.write(org.jamon.emit.StandardEmitter.valueOf(System.getProperty("java.version")), jamonWriter);
        // 149, 76
        jamonWriter.write(" is known to be\n          unstable with HBase. Please see the\n          <a href=\"http://wiki.apache.org/hadoop/Hbase/Troubleshooting#A18\">HBase wiki</a>\n          for details.\n          </div>\n        ");
      }
      // 154, 15
      jamonWriter.write("\n        ");
      // 155, 9
      if (master.isInitialized() && !catalogJanitorEnabled )
      {
        // 155, 65
        jamonWriter.write("\n          <div class=\"alert alert-error\">\n          Please note that your cluster is running with the CatalogJanitor disabled. It can be\n          re-enabled from the hbase shell by running the command 'catalogjanitor_switch true'\n          </div>\n        ");
      }
      // 160, 15
      jamonWriter.write("\n\n        <section>\n            <h2>Region Servers</h2>\n            ");
      // 164, 13
      {
        org.apache.hadoop.hbase.tmpl.master.RegionServerListTmpl __jamon__var_1 = new org.apache.hadoop.hbase.tmpl.master.RegionServerListTmpl(this.getTemplateManager());
        __jamon__var_1.setServers(servers );
        __jamon__var_1.renderNoFlush(jamonWriter, master);
      }
      // 164, 74
      jamonWriter.write("\n\n            ");
      // 166, 13
      if ((deadServers != null) )
      {
        // 166, 42
        jamonWriter.write("\n                ");
        // 167, 17
        {
          // 167, 17
          __jamon_innerUnit__deadRegionServers(jamonWriter);
        }
        // 167, 40
        jamonWriter.write("\n            ");
      }
      // 168, 19
      jamonWriter.write("\n        </section>\n        <section>\n            ");
      // 171, 13
      {
        org.apache.hadoop.hbase.tmpl.master.BackupMasterStatusTmpl __jamon__var_2 = new org.apache.hadoop.hbase.tmpl.master.BackupMasterStatusTmpl(this.getTemplateManager());
        __jamon__var_2.renderNoFlush(jamonWriter, master );
      }
      // 171, 58
      jamonWriter.write("\n        </section>\n        <section>\n            <h2>Tables</h2>\n            <div class=\"tabbable\">\n                <ul class=\"nav nav-pills\">\n                    <li class=\"active\">\n                        <a href=\"#tab_userTables\" data-toggle=\"tab\">User Tables</a>\n                    </li>\n                    <li class=\"\">\n                        <a href=\"#tab_catalogTables\" data-toggle=\"tab\">System Tables</a>\n                    </li>\n                    <li class=\"\">\n                        <a href=\"#tab_userSnapshots\" data-toggle=\"tab\">Snapshots</a>\n                    </li>\n                </ul>\n                <div class=\"tab-content\" style=\"padding-bottom: 9px; border-bottom: 1px solid #ddd;\">\n                    <div class=\"tab-pane active\" id=\"tab_userTables\">\n                        ");
      // 189, 25
      if ((metaLocation != null) )
      {
        // 189, 55
        jamonWriter.write("\n                            ");
        // 190, 29
        {
          // 190, 29
          __jamon_innerUnit__userTables(jamonWriter);
        }
        // 190, 45
        jamonWriter.write("\n                        ");
      }
      // 191, 31
      jamonWriter.write("\n                    </div>\n                    <div class=\"tab-pane\" id=\"tab_catalogTables\">\n                        ");
      // 194, 25
      if ((metaLocation != null) )
      {
        // 194, 55
        jamonWriter.write("\n                            ");
        // 195, 29
        {
          // 195, 29
          __jamon_innerUnit__catalogTables(jamonWriter);
        }
        // 195, 48
        jamonWriter.write("\n                        ");
      }
      // 196, 31
      jamonWriter.write("\n                    </div>\n                    <div class=\"tab-pane\" id=\"tab_userSnapshots\">\n                        ");
      // 199, 25
      {
        // 199, 25
        __jamon_innerUnit__userSnapshots(jamonWriter);
      }
      // 199, 44
      jamonWriter.write("\n                    </div>\n                </div>\n            </div>\n        </section>\n        ");
      // 204, 9
      {
        org.apache.hadoop.hbase.tmpl.master.AssignmentManagerStatusTmpl __jamon__var_3 = new org.apache.hadoop.hbase.tmpl.master.AssignmentManagerStatusTmpl(this.getTemplateManager());
        __jamon__var_3.renderNoFlush(jamonWriter, master.getAssignmentManager());
      }
      // 204, 90
      jamonWriter.write("\n\t");
    }
    // 205, 2
    else
    {
      // 205, 9
      jamonWriter.write("\n        <section>\n            ");
      // 207, 13
      {
        org.apache.hadoop.hbase.tmpl.master.BackupMasterStatusTmpl __jamon__var_4 = new org.apache.hadoop.hbase.tmpl.master.BackupMasterStatusTmpl(this.getTemplateManager());
        __jamon__var_4.renderNoFlush(jamonWriter, master );
      }
      // 207, 58
      jamonWriter.write("\n        </section>\n\t");
    }
    // 209, 8
    jamonWriter.write("    \n\n\n        <section>\n            ");
    // 213, 13
    {
      org.apache.hadoop.hbase.tmpl.common.TaskMonitorTmpl __jamon__var_5 = new org.apache.hadoop.hbase.tmpl.common.TaskMonitorTmpl(this.getTemplateManager());
      __jamon__var_5.setFilter(filter );
      __jamon__var_5.renderNoFlush(jamonWriter);
    }
    // 213, 61
    jamonWriter.write("\n        </section>\n\n        <section>\n            <h2>Software Attributes</h2>\n            <table id=\"attributes_table\" class=\"table table-striped\">\n                <tr>\n                    <th>Attribute Name</th>\n                    <th>Value</th>\n                    <th>Description</th>\n                </tr>\n                <tr>\n                    <td>HBase Version</td>\n                    <td>");
    // 226, 25
    org.jamon.escaping.Escaping.NONE.write(org.jamon.emit.StandardEmitter.valueOf(org.apache.hadoop.hbase.util.VersionInfo.getVersion()), jamonWriter);
    // 226, 84
    jamonWriter.write(", r");
    // 226, 87
    org.jamon.escaping.Escaping.NONE.write(org.jamon.emit.StandardEmitter.valueOf(org.apache.hadoop.hbase.util.VersionInfo.getRevision()), jamonWriter);
    // 226, 147
    jamonWriter.write("</td><td>HBase version and revision</td>\n                </tr>\n                <tr>\n                    <td>HBase Compiled</td>\n                    <td>");
    // 230, 25
    org.jamon.escaping.Escaping.NONE.write(org.jamon.emit.StandardEmitter.valueOf(org.apache.hadoop.hbase.util.VersionInfo.getDate()), jamonWriter);
    // 230, 81
    jamonWriter.write(", ");
    // 230, 83
    org.jamon.escaping.Escaping.NONE.write(org.jamon.emit.StandardEmitter.valueOf(org.apache.hadoop.hbase.util.VersionInfo.getUser()), jamonWriter);
    // 230, 139
    jamonWriter.write("</td>\n                    <td>When HBase version was compiled and by whom</td>\n                </tr>\n                <tr>\n                    <td>Hadoop Version</td>\n                    <td>");
    // 235, 25
    org.jamon.escaping.Escaping.NONE.write(org.jamon.emit.StandardEmitter.valueOf(org.apache.hadoop.util.VersionInfo.getVersion()), jamonWriter);
    // 235, 78
    jamonWriter.write(", r");
    // 235, 81
    org.jamon.escaping.Escaping.NONE.write(org.jamon.emit.StandardEmitter.valueOf(org.apache.hadoop.util.VersionInfo.getRevision()), jamonWriter);
    // 235, 135
    jamonWriter.write("</td>\n                    <td>Hadoop version and revision</td>\n                </tr>\n                <tr>\n                    <td>Hadoop Compiled</td>\n                    <td>");
    // 240, 25
    org.jamon.escaping.Escaping.NONE.write(org.jamon.emit.StandardEmitter.valueOf(org.apache.hadoop.util.VersionInfo.getDate()), jamonWriter);
    // 240, 75
    jamonWriter.write(", ");
    // 240, 77
    org.jamon.escaping.Escaping.NONE.write(org.jamon.emit.StandardEmitter.valueOf(org.apache.hadoop.util.VersionInfo.getUser()), jamonWriter);
    // 240, 127
    jamonWriter.write("</td>\n                    <td>When Hadoop version was compiled and by whom</td>\n                </tr>\n                <tr>\n                    <td>Zookeeper Quorum</td>\n                    <td> ");
    // 246, 26
    org.jamon.escaping.Escaping.NONE.write(org.jamon.emit.StandardEmitter.valueOf(formatZKString()), jamonWriter);
    // 246, 48
    jamonWriter.write(" </td>\n                    <td>Addresses of all registered ZK servers. For more, see <a href=\"/zk.jsp\">zk dump</a>.</td>\n                </tr>\n                <tr>\n                    <td>HBase Root Directory</td>\n                    <td>");
    // 251, 25
    org.jamon.escaping.Escaping.NONE.write(org.jamon.emit.StandardEmitter.valueOf(FSUtils.getRootDir(master.getConfiguration()).toString()), jamonWriter);
    // 251, 87
    jamonWriter.write("</td>\n                    <td>Location of HBase home directory</td>\n                </tr>\n                <tr>\n                    <td>HMaster Start Time</td>\n                    <td>");
    // 256, 25
    org.jamon.escaping.Escaping.NONE.write(org.jamon.emit.StandardEmitter.valueOf(new Date(master.getMasterStartTime())), jamonWriter);
    // 256, 68
    jamonWriter.write("</td>\n                    <td>Date stamp of when this HMaster was started</td>\n                </tr>\n                ");
    // 259, 17
    if (master.isActiveMaster() )
    {
      // 259, 48
      jamonWriter.write("\n\t                <tr>\n\t                    <td>HMaster Active Time</td>\n\t                    <td>");
      // 262, 26
      org.jamon.escaping.Escaping.NONE.write(org.jamon.emit.StandardEmitter.valueOf(new Date(master.getMasterActiveTime())), jamonWriter);
      // 262, 70
      jamonWriter.write("</td>\n\t                    <td>Date stamp of when this HMaster became active</td>\n\t                </tr>\n\t                <tr>\n\t                    <td>HBase Cluster ID</td>\n\t                    <td>");
      // 267, 26
      org.jamon.escaping.Escaping.NONE.write(org.jamon.emit.StandardEmitter.valueOf(master.getClusterId() != null ? master.getClusterId() : "Not set"), jamonWriter);
      // 267, 97
      jamonWriter.write("</td>\n\t                    <td>Unique identifier generated for each HBase cluster</td>\n\t                </tr>\n\t                <tr>\n\t                    <td>Load average</td>\n\t                    <td>");
      // 272, 26
      org.jamon.escaping.Escaping.NONE.write(org.jamon.emit.StandardEmitter.valueOf(StringUtils.limitDecimalTo2(master.getServerManager().getAverageLoad())), jamonWriter);
      // 272, 103
      jamonWriter.write("</td>\n\t                    <td>Average number of regions per regionserver. Naive computation.</td>\n\t                </tr>\n\t                ");
      // 275, 18
      if (frags != null )
      {
        // 275, 39
        jamonWriter.write("\n\t                <tr>\n\t                    <td>Fragmentation</td>\n\t                    <td>");
        // 278, 26
        org.jamon.escaping.Escaping.NONE.write(org.jamon.emit.StandardEmitter.valueOf(frags.get("-TOTAL-") != null ? frags.get("-TOTAL-").intValue() + "%" : "n/a"), jamonWriter);
        // 278, 108
        jamonWriter.write("</td>\n\t                    <td>Overall fragmentation of all tables, including hbase:meta</td>\n\t                </tr>\n\t                ");
      }
      // 281, 24
      jamonWriter.write("\n\t                <tr>\n\t                    <td>Coprocessors</td>\n\t                    <td>");
      // 284, 26
      org.jamon.escaping.Escaping.NONE.write(org.jamon.emit.StandardEmitter.valueOf(java.util.Arrays.toString(master.getCoprocessors())), jamonWriter);
      // 284, 83
      jamonWriter.write("</td>\n\t                    <td>Coprocessors currently loaded by the master</td>\n\t                </tr>\n                ");
    }
    // 287, 23
    jamonWriter.write("\n            </table>\n        </section>\n        </div>\n    </div> <!-- /container -->\n\n    <script src=\"/static/js/jquery.min.js\" type=\"text/javascript\"></script>\n    <script src=\"/static/js/bootstrap.min.js\" type=\"text/javascript\"></script>\n    <script src=\"/static/js/tab.js\" type=\"text/javascript\"></script>\n  </body>\n</html>\n\n");
  }
  
  
  // 370, 1
  private void __jamon_innerUnit__userSnapshots(@SuppressWarnings({"unused","hiding"}) final java.io.Writer jamonWriter)
    throws java.io.IOException
  {
    // 371, 1
    
   List<SnapshotDescription> snapshots = admin.listSnapshots();

    // 374, 1
    if ((snapshots != null && snapshots.size() > 0))
    {
      // 374, 51
      jamonWriter.write("\n<table class=\"table table-striped\">\n    <tr>\n        <th>Snapshot Name</th>\n        <th>Table</th>\n        <th>Creation Time</th>\n    </tr>\n    ");
      // 381, 5
      for (SnapshotDescription snapshotDesc : snapshots)
      {
        // 381, 57
        jamonWriter.write("\n    ");
        // 382, 5
        
        TableName snapshotTable = TableName.valueOf(snapshotDesc.getTable());
    
        // 385, 5
        jamonWriter.write("<tr>\n        <td><a href=\"snapshot.jsp?name=");
        // 386, 40
        org.jamon.escaping.Escaping.NONE.write(org.jamon.emit.StandardEmitter.valueOf(snapshotDesc.getName()), jamonWriter);
        // 386, 68
        jamonWriter.write("\">");
        // 386, 70
        org.jamon.escaping.Escaping.NONE.write(org.jamon.emit.StandardEmitter.valueOf(snapshotDesc.getName()), jamonWriter);
        // 386, 98
        jamonWriter.write("</a> </td>\n        <td><a href=\"table.jsp?name=");
        // 387, 37
        org.jamon.escaping.Escaping.NONE.write(org.jamon.emit.StandardEmitter.valueOf(snapshotTable.getNameAsString()), jamonWriter);
        // 387, 74
        jamonWriter.write("\">");
        // 387, 76
        org.jamon.escaping.Escaping.NONE.write(org.jamon.emit.StandardEmitter.valueOf(snapshotTable.getNameAsString()), jamonWriter);
        // 387, 113
        jamonWriter.write("</a>\n        </td>\n        <td>");
        // 389, 13
        org.jamon.escaping.Escaping.NONE.write(org.jamon.emit.StandardEmitter.valueOf(new Date(snapshotDesc.getCreationTime())), jamonWriter);
        // 389, 59
        jamonWriter.write("</td>\n    </tr>\n    ");
      }
      // 391, 12
      jamonWriter.write("\n    <p>");
      // 392, 8
      org.jamon.escaping.Escaping.NONE.write(org.jamon.emit.StandardEmitter.valueOf(snapshots.size()), jamonWriter);
      // 392, 30
      jamonWriter.write(" snapshot(s) in set.</p>\n</table>\n");
    }
    // 394, 7
    jamonWriter.write("\n");
  }
  
  
  // 299, 1
  private void __jamon_innerUnit__catalogTables(@SuppressWarnings({"unused","hiding"}) final java.io.Writer jamonWriter)
    throws java.io.IOException
  {
    // 300, 1
    
 HTableDescriptor[] sysTables = admin.listTableDescriptorsByNamespace(NamespaceDescriptor
 .SYSTEM_NAMESPACE_NAME_STR);

    // 305, 1
    jamonWriter.write("<table class=\"table table-striped\">\n<tr>\n    <th>Table Name</th>\n    ");
    // 308, 5
    if ((frags != null) )
    {
      // 308, 28
      jamonWriter.write("\n        <th title=\"Fragmentation - Will be 0% after a major compaction and fluctuate during normal usage.\">Frag.</th>\n    ");
    }
    // 310, 11
    jamonWriter.write("\n    <th>Description</th>\n</tr>\n");
    // 313, 1
    for (HTableDescriptor systemTable : sysTables)
    {
      // 313, 49
      jamonWriter.write("\n<tr>\n");
      // 315, 1
      TableName tableName = systemTable.getTableName();
      // 316, 5
      jamonWriter.write("<td><a href=\"table.jsp?name=");
      // 316, 33
      org.jamon.escaping.Escaping.NONE.write(org.jamon.emit.StandardEmitter.valueOf(tableName), jamonWriter);
      // 316, 48
      jamonWriter.write("\">");
      // 316, 50
      org.jamon.escaping.Escaping.NONE.write(org.jamon.emit.StandardEmitter.valueOf(tableName), jamonWriter);
      // 316, 65
      jamonWriter.write("</a></td>\n    ");
      // 317, 5
      if ((frags != null))
      {
        // 317, 27
        jamonWriter.write("\n        <td align=\"center\">");
        // 318, 28
        org.jamon.escaping.Escaping.NONE.write(org.jamon.emit.StandardEmitter.valueOf(frags.get(tableName.getNameAsString()) != null ? frags.get(tableName.getNameAsString())
        .intValue() + "%" : "n/a"), jamonWriter);
        // 319, 37
        jamonWriter.write("</td>\n    ");
      }
      // 320, 11
      jamonWriter.write("\n    ");
      // 321, 5
      String description = null;
        if (tableName.equals(TableName.META_TABLE_NAME)){
            description = "The hbase:meta table holds references to all User Table regions";
        } else if (tableName.equals(AccessControlLists.ACL_TABLE_NAME)){
            description = "The hbase:acl table holds information about acl";
	 } else if (tableName.equals(VisibilityConstants.LABELS_TABLE_NAME)){
	     description = "The hbase:labels table holds information about visibility labels";
        } else {
            description = "The .NAMESPACE. table holds information about namespaces.";
        }
    
      // 332, 5
      jamonWriter.write("<td>");
      // 332, 9
      org.jamon.escaping.Escaping.NONE.write(org.jamon.emit.StandardEmitter.valueOf(description), jamonWriter);
      // 332, 26
      jamonWriter.write("</td>\n</tr>\n");
    }
    // 334, 8
    jamonWriter.write("\n</table>\n");
  }
  
  
  // 338, 1
  private void __jamon_innerUnit__userTables(@SuppressWarnings({"unused","hiding"}) final java.io.Writer jamonWriter)
    throws java.io.IOException
  {
    // 339, 1
    
   HTableDescriptor[] tables = admin.listTables();

    // 342, 1
    if ((tables != null && tables.length > 0))
    {
      // 342, 45
      jamonWriter.write("\n<table class=\"table table-striped\">\n    <tr>\n        <th>Namespace</th>\n        <th>Table Name</th>\n        ");
      // 347, 9
      if ((frags != null) )
      {
        // 347, 32
        jamonWriter.write("\n            <th title=\"Fragmentation - Will be 0% after a major compaction and fluctuate during normal usage.\">Frag.</th>\n        ");
      }
      // 349, 15
      jamonWriter.write("\n        <th>Online Regions</th>\n        <th>Description</th>\n    </tr>\n    ");
      // 353, 5
      for (HTableDescriptor htDesc : tables)
      {
        // 353, 45
        jamonWriter.write("\n    <tr>\n        <td>");
        // 355, 13
        org.jamon.escaping.Escaping.NONE.write(org.jamon.emit.StandardEmitter.valueOf(htDesc.getTableName().getNamespaceAsString()), jamonWriter);
        // 355, 63
        jamonWriter.write("</td>\n        <td><a href=table.jsp?name=");
        // 356, 36
        org.jamon.escaping.Escaping.NONE.write(org.jamon.emit.StandardEmitter.valueOf(htDesc.getTableName().getNameAsString()), jamonWriter);
        // 356, 81
        jamonWriter.write(">");
        // 356, 82
        org.jamon.escaping.Escaping.NONE.write(org.jamon.emit.StandardEmitter.valueOf(htDesc.getTableName().getQualifierAsString()), jamonWriter);
        // 356, 132
        jamonWriter.write("</a> </td>\n        ");
        // 357, 9
        if ((frags != null) )
        {
          // 357, 32
          jamonWriter.write("\n            <td align=\"center\">");
          // 358, 32
          org.jamon.escaping.Escaping.NONE.write(org.jamon.emit.StandardEmitter.valueOf(frags.get(htDesc.getTableName().getNameAsString()) != null ? frags.get(htDesc.getTableName().getQualifierAsString()).intValue() + "%" : "n/a"), jamonWriter);
          // 358, 179
          jamonWriter.write("</td>\n        ");
        }
        // 359, 15
        jamonWriter.write("\n        <td>");
        // 360, 13
        org.jamon.escaping.Escaping.NONE.write(org.jamon.emit.StandardEmitter.valueOf(master.getAssignmentManager().getRegionStates().getRegionsOfTable(htDesc
        .getTableName()).size()), jamonWriter);
        // 361, 35
        jamonWriter.write("\n        <td>");
        // 362, 13
        org.jamon.escaping.Escaping.NONE.write(org.jamon.emit.StandardEmitter.valueOf(htDesc.toStringCustomizedValues()), jamonWriter);
        // 362, 52
        jamonWriter.write("</td>\n    </tr>\n    ");
      }
      // 364, 12
      jamonWriter.write("\n    <p>");
      // 365, 8
      org.jamon.escaping.Escaping.NONE.write(org.jamon.emit.StandardEmitter.valueOf(tables.length), jamonWriter);
      // 365, 27
      jamonWriter.write(" table(s) in set. [<a href=tablesDetailed.jsp>Details</a>]</p>\n</table>\n");
    }
    // 367, 7
    jamonWriter.write("\n");
  }
  
  
  // 398, 1
  private void __jamon_innerUnit__deadRegionServers(@SuppressWarnings({"unused","hiding"}) final java.io.Writer jamonWriter)
    throws java.io.IOException
  {
    // 400, 1
    if ((deadServers != null && deadServers.size() > 0))
    {
      // 400, 55
      jamonWriter.write("\n<h2>Dead Region Servers</h2>\n<table class=\"table table-striped\">\n    <tr>\n        <th></th>\n        <th>ServerName</th>\n        <th>Stop time</th>\n    </tr>\n    ");
      // 408, 5
      
       DeadServer deadServerUtil = master.getServerManager().getDeadServers();
       ServerName [] deadServerNames = deadServers.toArray(new ServerName[deadServers.size()]);
         Arrays.sort(deadServerNames);
         for (ServerName deadServerName: deadServerNames) {
    
      // 414, 5
      jamonWriter.write("<tr>\n    \t<th></th>\n        <td>");
      // 416, 13
      org.jamon.escaping.Escaping.NONE.write(org.jamon.emit.StandardEmitter.valueOf(deadServerName), jamonWriter);
      // 416, 33
      jamonWriter.write("</td>\n        <td>");
      // 417, 13
      org.jamon.escaping.Escaping.NONE.write(org.jamon.emit.StandardEmitter.valueOf(deadServerUtil.getTimeOfDeath(deadServerName)), jamonWriter);
      // 417, 64
      jamonWriter.write("</td>\n    </tr>\n    ");
      // 419, 5
      
        }
    
      // 422, 5
      jamonWriter.write("<tr>\n        <th>Total: </th>\n        <td>servers: ");
      // 424, 22
      org.jamon.escaping.Escaping.NONE.write(org.jamon.emit.StandardEmitter.valueOf(deadServers.size()), jamonWriter);
      // 424, 46
      jamonWriter.write("</td>\n        <th></th>\n    </tr>\n</table>\n");
    }
    // 428, 7
    jamonWriter.write("\n");
    // 429, 1
    
   HConnectionManager.deleteConnection(admin.getConfiguration());

  }
  
  
}
