require "./paso_1/expresion_regular_trope"

MAX_PARSE_ITEMS = 20

def parsear(ruta, expresion)
  # Cargamos el contenido
  contents = File.open(ruta, "rb").read
  # Devolvemos el array
  return contents.scan(expresion)
end

def cargar_series(carpeta_series, conPuntos = nil, series = {})
  # Solo pillar los html. Ordenarlos alfabeticamente.
  rutas_series = Dir[carpeta_series+"/*.html"]
  rutas_series.sort!

  progress = 0
  rutas_series.each do |ruta|
    nombre = File.basename(ruta, File.extname(ruta))
    # "Arrow" => ["AbandonedWarehouse", "LoveHurts"]
    series[nombre] = parsear(ruta, expresion_regular_trope(conPuntos))
    # Avisamos del progreso
    progress += 1
    puts "series cargadas: #{progress}"
    break if progress == MAX_PARSE_ITEMS
    end

  return series
end
