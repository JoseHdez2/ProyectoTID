load "OpcionesEstudio.rb"
load "ParseadorTropes.rb"
load "ClasificadorTropes.rb"
load "GeneradorWeka.rb"
require "pry"

GENERO_DESCONOCIDO = "Desconocido"


def estudiarSeries
  opciones = OpcionesEstudio.new()
  carpetaTropes = opciones.carpetaTropes
  carpetaGeneros = opciones.carpetaGeneros
  rutaWeka = opciones.rutaWeka
  #1
  series = ParseadorTropes.parsearCarpeta(carpetaTropes)
  #2
  generos = ParseadorTropes.parsearCarpeta(carpetaGeneros)
  #3
  hashMaestro = ClasificadorTropes.darClasificacion(generos, GENERO_DESCONOCIDO)
  #4
  series = ClasificadorTropes.darConteo(series, hashMaestro)
  #5
  GeneradorWeka.escribir(rutaWeka, series, generos)
end

estudiarSeries
