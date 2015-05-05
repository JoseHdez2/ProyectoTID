
# Load all .rb files in current dir and subdirs (that are not this one!)
THIS_FILE = "./main.rb"
Dir["./**/*.rb"].each { |file| require file if file != THIS_FILE }

require "pry"

GENERO_DESCONOCIDO = "Desconocido"

CARPETA_SERIES = "/home/jose/Documents/tid/raw/series"
CARPETA_GENEROS = "/home/jose/Documents/tid/raw/generos"
ARCHIVO_WEKA = "/home/jose/Documents/tid/weka/tropes.arff"
ARCHIVO_CLASS_SERIES = "/home/jose/Documents/tid/pro/csv/class_series.csv"

CLASIFICAR_TROPES_CON_GENEROS = true
CLASIFICAR_TROPES_CON_SERIES = false

DECIMALES_REDONDEO = 3

def estudiar_series
  puts "paso 1: cargar series"
  series = cargar_series(CARPETA_SERIES, true)

  puts "paso 2: clasificar tropes"
  generos = {}
  if CLASIFICAR_TROPES_CON_GENEROS
    generos = cargar_series(CARPETA_GENEROS, generos)
  end
  if CLASIFICAR_TROPES_CON_SERIES
    clases_series = hash_from_csv(ARCHIVO_CLASS_SERIES)
    generos = clases_series_a_generos(clases_series, series, generos)
  end
  clasificacion_tropes = clasificar_tropes(series, generos)

  puts "paso 3: clasificar series"
  clasificacion_series = clasificar_series(clasificacion_tropes, series)

  puts "paso 4: escribir archivo Weka"
  escribir_weka(ARCHIVO_WEKA, clasificacion_series)

end

estudiar_series
