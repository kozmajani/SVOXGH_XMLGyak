<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:template match="/">
<html> 
<body>
  <h2>Hallgatók choose</h2>
  <table border="1">
    <tr bgcolor="#9acd32">
      <th style="text-align:left">ID</th>
      <th style="text-align:left">Vezeteknev</th>
      <th style="text-align:left">Keresztnev</th>
      <th style="text-align:left">Becenev</th>
      <th style="text-align:left">Kor</th>
      <th style="text-align:left">Fizetes</th>
	  <th style="text-align:left">Fokozat</th>
    </tr>
    <xsl:for-each select="class/student">
    <tr>
      <td><xsl:value-of select="@id"/></td>
      <td><xsl:value-of select="vezeteknev"/></td>
      <td><xsl:value-of select="keresztnev"/></td>
      <td><xsl:value-of select="becenev"/></td>
      <td><xsl:value-of select="kor"/></td>
      <td><xsl:value-of select="fizetes"/></td>
	  <xsl:choose>
		  <xsl:when test="fizetes >= 85000">
		    <td>High</td>
		  </xsl:when>
		  <xsl:when test="fizetes >= 60000">
		  	<td>Medium</td>
		  </xsl:when>
		  <xsl:otherwise>
		  	<td>Low</td>
		  </xsl:otherwise>
	  </xsl:choose> 
    </tr>
    </xsl:for-each>
  </table>
</body>
</html>
</xsl:template>


<xsl:template match="@id">
  ID: <span style="color:#000000">
  <xsl:value-of select="."/></span>
</xsl:template>

<xsl:template match="vezeteknev">
  Vezeteknev: <span style="color:#00ff33">
  <xsl:value-of select="."/></span>
</xsl:template>

<xsl:template match="keresztnev">
  Keresztnev: <span style="color:#880000">
  <xsl:value-of select="."/></span>
</xsl:template>

<xsl:template match="becenev">
  Becenev: <span style="color:#ffe701">
  <xsl:value-of select="."/></span>
</xsl:template>

<xsl:template match="kor">
  Kor: <span style="color:#003cff">
  <xsl:value-of select="."/></span>
</xsl:template>

<xsl:template match="fizetes">
  Fizetes: <span style="color:#ff0000">
  <xsl:value-of select="."/></span>
</xsl:template>

</xsl:stylesheet>

