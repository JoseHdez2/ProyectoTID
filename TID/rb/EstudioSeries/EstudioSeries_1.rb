
class EstudioSeries
  #
  # 1er paso: Cargar las series en un array.
  #
  def cargarSeries(carpetaTropes, modo)

    # Colocamos las dir de los HTML en un array.
    dirsSeries = Dir[carpetaTropes+"/*.html"]

    # Ordenamos los archivos alfabéticamente.
    dirsSeries.sort!

    # Por cada archivo,
    # crear una serie y agregarla al array.
    count = 0
    dirsSeries.each do |dir|
      @series.push(Serie.new(dir, modo))
      count += 1
      puts "series cargadas: #{count}"
    end
  end
end
