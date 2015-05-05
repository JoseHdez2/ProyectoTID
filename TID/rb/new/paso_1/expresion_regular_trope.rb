def expresion_regular_trope(conPuntos = nil)
  # El lookAhead. Quiero pillar lo que tenga esto antes.
  regexStrings = "(?<=title='http://tvtropes.org/pmwiki/pmwiki.php/Main/)"
  # El "cuerpo". Quiero pillar esto.
  regexStrings += "[^']+"
  # El lookBehind. Quiero pillar lo que tenga esto después.
  regexStrings += "(?='>[^<]+</a>"

  # conPuntos. nil = puntos opcionales. Casa con y sin puntos.
  regexStrings += ":" if conPuntos == true
  regexStrings += ":?" if conPuntos == nil
  regexStrings += ")"

  # Añadimos los caracteres de escape necesarios.
  regexStrings.gsub("/","\\/")

  return Regexp.new(regexStrings)
end
