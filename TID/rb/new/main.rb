
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

def estudiar_series
  # 1 # TODO con puntos?? o indiferente?!
  series = cargar_series(CARPETA_SERIES, true)
  # 2
  generos = {}
  if CLASIFICAR_TROPES_CON_GENEROS
    generos = cargar_series(CARPETA_GENEROS, generos)
  end
  if CLASIFICAR_TROPES_CON_SERIES
    clases_series = hash_from_csv(ARCHIVO_CLASS_SERIES)
    generos2 = clases_series_a_generos(clases_series, series, generos)
  end
  # 3
  clasificacion_tropes = clasificar_tropes(series, generos)
  # 4
  binding.pry
end

estudiar_series
