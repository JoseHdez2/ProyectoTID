
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
  #2 clasificar
  hashMaestro = clasificar_tropes_usando_generos(generos)
  #series = clasificar_series(series, hash_from_csv(rutaClassSeries)) # Asignamos genero a cada serie
  #hashMaestro = clasificar_tropes_usando_series(series)
  #3 contar
  series = contar_tropes(series, hashMaestro)
  #4 guardar
  escribir_weka(rutaWeka, series, generos)
end

if not Dir[File.dirname(ARCHIVO_WEKA)].empty?
  estudiarSeries
else
  puts "El directorio Weka no esta vacio! Creacion cancelada."
end
