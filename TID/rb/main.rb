
# Load all .rb files in subdirectories
Dir["./**/*.rb"].each {|file| require file }

require "pry"

GENERO_DESCONOCIDO = "Desconocido"


def estudiarSeries
  opciones = OpcionesEstudio.new()
  carpetaTropes = opciones.carpetaTropes
  carpetaGeneros = opciones.carpetaGeneros
  rutaWeka = opciones.rutaWeka
  #1
  series = ParseadorTropes.parsearCarpeta(carpetaTropes)
  generos = ParseadorTropes.parsearCarpeta(carpetaGeneros)
  #2
  hashMaestro = clasificar_tropes(generos, GENERO_DESCONOCIDO)
  #3
  series = contar_tropes(series, hashMaestro)
  #4
  escribir_weka(rutaWeka, series, generos)
end

if not Dir[File.dirname(ARCHIVO_WEKA)].empty?
  estudiarSeries
else
  puts "El directorio Weka no esta vacio! Creacion cancelada."
end
