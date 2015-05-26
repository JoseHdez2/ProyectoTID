
# Load all .rb files in current dir and subdirs (that are not this one!)
THIS_FILE = "./main.rb"
Dir["./**/*.rb"].each { |file| require file if file != THIS_FILE }

require "pry"

GENERO_DESCONOCIDO = "Desconocido"

def estudiarSeries
  opciones = OpcionesEstudio.new()
  carpetaTropes = opciones.carpetaTropes
  carpetaGeneros = opciones.carpetaGeneros
  rutaWeka = opciones.rutaWeka
  rutaClassSeries = opciones.rutaClassSeries
  #1 cargar
  series = ParseadorTropes.parsearCarpeta(carpetaTropes, true)
  generos = ParseadorTropes.parsearCarpeta(carpetaGeneros, nil)
  generos.each { |g| g.genero = g.nombre }
  #2 clasificar
  #clasificacion_tropes = clasificar_tropes_usando_series(generos)
  series = clasificar_series(series, hash_from_csv(rutaClassSeries)) # Asignamos genero a cada serie
  clasificacion_tropes = clasificar_tropes_usando_series(series)
  #3 contar
  series = contar_tropes(series, clasificacion_tropes)
  
  #4 guardar
  escribir_weka(rutaWeka, series, clasificacion_tropes)
end

estudiarSeries
