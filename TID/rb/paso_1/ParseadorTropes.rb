load "./paso_1/Parseador.rb"

MAX_PARSE_ITEMS = 9001

class ParseadorTropes < Parseador

  def self.expresionTrope()
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

  def self.parsear(ruta)
    Parseador.parsear(ruta, self.expresionTrope())
  end

  def self.leerSerie(ruta)
    # Asignamos como nombre el nombre del archivo.
    nombre = File.basename(ruta, File.extname(ruta))
    return Serie.new(nombre, self.leer(ruta))
  end

  def self.parsearSerie(ruta)
    nombre = File.basename(ruta, File.extname(ruta))
    return Serie.new(nombre, self.parsear(ruta))
  end

  def self.parsearCarpeta(carpeta)
    rutas_series = Dir[carpeta+"/*.html"]
    rutas_series.sort!

    series = []
    count = 0
    rutas_series.each do |ruta|
      series.push(ParseadorTropes.parsearSerie(ruta))
      count += 1
      puts "series cargadas: #{count}"
      if count == MAX_PARSE_ITEMS
        break
      end
    end

    return series
  end
end
