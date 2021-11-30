<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:template match="/">
<html> 
<body>
    <h2>30.000-nél drágább autók száma</h2>
  <table border="1">
    <tr bgcolor="#9acd32">
      <th style="text-align:left">> 30.000</th> 
    </tr>
    <tr>
    	<td style="text-align:center"><xsl:value-of select="count(/autok/auto[ar > 30000])" /></td>
    </tr>
  </table>
</body>
</html>
</xsl:template>



</xsl:stylesheet>


