<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:template match="/">
<html> 
<body>
  <table border="1">
    <h2>Rendszám és ár, ár szerint növekvő sorrendben</h2>
    <tr bgcolor="#9acd32">
      <th style="text-align:left">Rendszám</th>
      <th style="text-align:left">Ár</th>   
    </tr>
    <xsl:for-each select="autok/auto">
    <xsl:sort select="ar" />
    <tr>
      <td><xsl:value-of select="@rsz"/></td>
      <td><xsl:value-of select="ar" /></td>
	</tr>
    </xsl:for-each>
  </table>
</body>
</html>
</xsl:template>


<xsl:template match="@rsz">
  Rendszam: <span style="color:#000000">
  <xsl:value-of select="."/></span>
</xsl:template>

<xsl:template match="ar">
  Ar: <span style="color:#000000">
  <xsl:value-of select="."/></span>
</xsl:template>

</xsl:stylesheet>


