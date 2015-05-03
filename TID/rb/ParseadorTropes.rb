class ParseadorTropes

  def self.makeRegexp()
    # El lookAhead. Quiero pillar lo que tenga esto antes.
    regexStrings = ["(?<=title='http://tvtropes.org/pmwiki/pmwiki.php/Main/)"]
    # El "cuerpo". Quiero pillar esto.
    regexStrings.push("[^']+")
    # El lookBehind. Quiero pillar lo que tenga esto después.
    regexStrings.push("(?='>[^<]+</a>:)")

    # Añadimos los caracteres de escape necesarios, y unimos todo en una sola cadena.
    regexStrings = regexStrings.collect { |s| s.gsub("/","\\/") }.join("")

    return Regexp.new(regexStrings)
  end

  def self.parsearArchivo(pathArchivo)
    # Leemos los contenidos del archivo.
    contents = File.open(pathArchivo, "rb").read

    # Creamos la expresión regular para capturar los tropes.
    tropeRegexp = self.makeRegexp()

    # Devolvemos lo que casa con la expresión.
    return contents.scan(tropeRegexp)
  end

  def self.leerArchivo(pathArchivo)
    # Leemos los contenidos del archivo.
    contents = File.open(pathArchivo, "rb").read

    # Suponiendo que el archivo consiste en los objetos separados por "\n"...
    # Devolvemos el array de objetos.
    return contents.split
  end
end
