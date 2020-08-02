package com.oocl.cultivation.test.story1;

import com.oocl.cultivation.story1.*;
import com.oocl.cultivation.story1.entity.Car;
import com.oocl.cultivation.story1.entity.Ticket;
import com.oocl.cultivation.story1.enums.ParkingFetchingEnums;
import com.oocl.cultivation.story1.exceptions.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class ParkingBoyFacts {

    private final String CAR_NULL_ERROR_MESSAGE = ParkingFetchingEnums.CAR_NULL_ERROR_MESSAGE.getMessage();
    private final String PARKING_CAR_HAVE_BEEN_PARKED = ParkingFetchingEnums.PARKING_CAR_HAVE_BEEN_PARKED.getMessage();
    private final String PARKING_HAVE_NO_SPACE = ParkingFetchingEnums.PARKING_HAVE_NO_SPACE.getMessage();
    private final String FETCHING_HAVE_NO_TICKET = ParkingFetchingEnums.FETCHING_HAVE_NO_TICKET.getMessage();
    private final String FETCHING_ERROR_TICKET = ParkingFetchingEnums.FETCHING_ERROR_TICKET.getMessage();

    @Test
    void should_return_ticket_when_park_given_car_pakingboy() {
        //given
        PackingBoy packingBoy = new PackingBoy();
        Car car = new Car("1234");

        //when
        Ticket ticket = packingBoy.parkCar(car);

        //then
        Assertions.assertEquals(Ticket.generateTicket(car), ticket);
    }

    @Test
    void should_return_2_ticket_when_park_given_car_parkingboy(){
        //given
        PackingBoy packingBoy = new PackingBoy();
        List<Car> carList = new ArrayList<>();
        carList.add(new Car("1234"));
        carList.add(new Car("2345"));

        //when
        List<Ticket> ticketList = packingBoy.parkCar(carList);

        //then
        Assertions.assertEquals(2, ticketList.size());
    }

    @Test
    void should_return_full_message_when_park_car_given_car_parkingboy_parkingspace_0(){
        //given
        PackingLot packingLot = new PackingLot(0);
        PackingBoy packingBoy = new PackingBoy(packingLot);
        Car car = new Car("1234");

        //when
        BaseException exception =
                Assertions.assertThrows(NotEnoughPositionException.class,() -> packingBoy.parkCar(car));

        //then
        Assertions.assertEquals(PARKING_HAVE_NO_SPACE,exception.getMessage());
    }

    @Test
    void should_return_wrong_message_when_park_car_given_parked_car_and_parkingboy(){
        //given
        PackingBoy packingBoy = new PackingBoy();
        Car car = new Car("1234");

        //when
        packingBoy.parkCar(car);
        BaseException exception = Assertions.assertThrows(CarHaveBeenParkedException.class, () -> packingBoy.parkCar(car));

        //then
        Assertions.assertEquals(PARKING_CAR_HAVE_BEEN_PARKED,exception.getMessage());
    }

    @Test
    void should_return_wrong_message_when_park_car_given_null_car_and_parkingboy(){
        //given
        PackingBoy packingBoy = new PackingBoy();
        Car car = null;

        //when
        BaseException exception =
                Assertions.assertThrows(CarIllegalException.class, () -> packingBoy.parkCar(car));

        //then
        Assertions.assertEquals(CAR_NULL_ERROR_MESSAGE,exception.getMessage());
    }

    @Test
    void should_return_current_ticket_when_fetch_car_given_2car_parkingboy_ticket() {
        //given
        PackingBoy packingBoy = new PackingBoy();

        List<Car> carList = new ArrayList<>();
        Car car1 = new Car("1234");
        Car car2 = new Car("2345");
        carList.add(car1);
        carList.add(car2);
        packingBoy.parkCar(carList);

        Ticket ticket = Ticket.generateTicket(car1);

        //when
        Car fetchCar = packingBoy.fetchCar(ticket);

        //then
        Assertions.assertEquals(car1.getCardId(), fetchCar.getCardId());
    }

    @Test
    void should_return_null_when_fetch_car_given_2car_parkingboy_wrong_ticket() {
        //given
        PackingBoy packingBoy = new PackingBoy();

        List<Car> carList = new ArrayList<>();
        Car car1 = new Car("1234");
        Car car2 = new Car("2345");
        carList.add(car1);
        carList.add(car2);
        packingBoy.parkCar(carList);

        Car carNoPacked = new Car("1324");
        Ticket ticket = Ticket.generateTicket(carNoPacked);

        //when
        BaseException exception = Assertions.assertThrows(TicketException.class, () -> packingBoy.fetchCar(ticket));

        //then
        Assertions.assertEquals(FETCHING_ERROR_TICKET, exception.getMessage());
    }

    @Test
    void should_return_wrong_message_when_fetch_car_given_2car_parkingboy_without_ticket() {
        //given
        PackingBoy packingBoy = new PackingBoy();
        Ticket ticket = null;

        List<Car> carList = new ArrayList<>();
        carList.add(new Car("1234"));
        carList.add(new Car("2345"));
        packingBoy.parkCar(carList);

        //when
        BaseException exception = Assertions.assertThrows(TicketException.class,() -> packingBoy.fetchCar(ticket));

        //then
        Assertions.assertEquals(FETCHING_HAVE_NO_TICKET,exception.getMessage());
    }

    @Test
    void should_return_null_when_fetch_car_given_2car_pakingboy_ticket_has_been_used() {
        //given
        PackingBoy packingBoy = new PackingBoy();

        List<Car> carList = new ArrayList<>();
        Car car1 = new Car("1234");
        Car car2 = new Car("2345");
        carList.add(car1);
        carList.add(car2);
        packingBoy.parkCar(carList);

        Ticket ticket = Ticket.generateTicket(car1);

        //when
        packingBoy.fetchCar(ticket);
        BaseException exception = Assertions.assertThrows(TicketException.class,() -> packingBoy.fetchCar(ticket));

        //then
        Assertions.assertEquals(FETCHING_ERROR_TICKET, exception.getMessage());
    }

    //TODO fix the unit test name
    @Test
    void should_return_true_when_park_car_given_fullParkingLot_unFullParkingLot_pakingboy_car() {
        //given
        List<PackingLot> packingLotList = new ArrayList<>();
        PackingLot fullPackingLot = new PackingLot(1);
        PackingLot unFullPackingLot = new PackingLot(2);
        packingLotList.add(fullPackingLot);
        packingLotList.add(unFullPackingLot);

        PackingBoy packingBoy = new PackingBoy(packingLotList);
        Car car = new Car("1234");

        //when
        packingBoy.parkCar(new Car("2345"));
        Ticket ticket = packingBoy.parkCar(car);

        //then
        Assertions.assertEquals(Ticket.generateTicket(car), ticket);
    }

    // 检验进来的时候第一个停车场满了，所以car停在第二个停车场，这时候第一个停车场走了一辆车，客户拿着已经停过的车再来停
    @Test
    void should_return_has_been_packed_message_when_park_car_given_unfullParkingLot_after_carParked_unFullParkingLot_pakingboy_and_2Car() {
        //given
        List<PackingLot> packingLotList = new ArrayList<>();
        PackingLot fullPackingLot = new PackingLot(1);
        PackingLot unFullPackingLot = new PackingLot(2);
        packingLotList.add(fullPackingLot);
        packingLotList.add(unFullPackingLot);

        PackingBoy packingBoy = new PackingBoy(packingLotList);
        Car car1 = new Car("1234");
        Car car2 = new Car("4567");

        //when
        Ticket ticket1 = packingBoy.parkCar(car1);
        packingBoy.parkCar(car2);
        packingBoy.fetchCar(ticket1);
        BaseException exception =
                Assertions.assertThrows(CarHaveBeenParkedException.class,() -> packingBoy.parkCar(car2));

        //then
        Assertions.assertEquals(PARKING_CAR_HAVE_BEEN_PARKED,exception.getMessage());
    }

    // story4
    @Test
    void should_return_true_when_parkingCar_and_find_in_parkingLot2_given_parkingLot1WithSpace5_and_parkingLot2WithSpace10_and_smallParkingBoy_and_car() {
        //given
        List<PackingLot> packingLotList = new ArrayList<>();
        PackingLot packingLot1 = new PackingLot(5);
        PackingLot packingLot2 = new PackingLot(10);
        packingLotList.add(packingLot1);
        packingLotList.add(packingLot2);

        AbstractPackingBoy smallPackingBoy = new SmallParkingBoy(packingLotList);
        Car car = new Car("1234");

        //when
        smallPackingBoy.parkCar(car);
        List<PackingLot> packingLotListInPackingBoy = smallPackingBoy.getPackingLots();
        boolean isFind = packingLotListInPackingBoy.get(1).isCarHaveBeenParked(car);

        //then
        Assertions.assertTrue(isFind);
    }

    @Test
    void should_return_true_when_parkingCar_and_find_car_in_parkingLot1_and_car2_in_parkingLot2_given_parkingLot1WithSpace5_and_parkingLot2WithSpace5_and_smallParkingBoy_and_2car() {
        //given
        List<PackingLot> packingLotList = new ArrayList<>();
        PackingLot packingLot1 = new PackingLot(5);
        PackingLot packingLot2 = new PackingLot(5);
        packingLotList.add(packingLot1);
        packingLotList.add(packingLot2);

        AbstractPackingBoy smallPackingBoy = new SmallParkingBoy(packingLotList);
        List<Car> carList = new ArrayList<>();
        Car car1 = new Car("1234");
        Car car2 = new Car("2345");
        carList.add(car1);
        carList.add(car2);

        //when
        smallPackingBoy.parkCar(carList);
        List<PackingLot> packingLotListInPackingBoy = smallPackingBoy.getPackingLots();
        boolean isFindCar1InParkingLot1 = packingLotListInPackingBoy.get(0).isCarHaveBeenParked(car1);
        boolean isFindCar2InParkingLot2 = packingLotListInPackingBoy.get(1).isCarHaveBeenParked(car2);

        //then
        Assertions.assertTrue(isFindCar1InParkingLot1 && isFindCar2InParkingLot2);
    }

    @Test
    void should_return_ticket_when_park_given_car_smallPakingboy() {
        //given
        AbstractPackingBoy smallPackingBoy = new SmallParkingBoy();
        Car car = new Car("1234");

        //when
        Ticket ticket = smallPackingBoy.parkCar(car);

        //then
        Assertions.assertEquals(Ticket.generateTicket(car),ticket);
    }

    @Test
    void should_return_2_ticket_when_park_given_car_smallParkingboy(){
        //given
        AbstractPackingBoy packingBoy = new SmallParkingBoy();
        List<Car> carList = new ArrayList<>();
        carList.add(new Car("1234"));
        carList.add(new Car("2345"));

        //when
        List<Ticket> ticketList = packingBoy.parkCar(carList);

        //then
        Assertions.assertEquals(2, ticketList.size());
    }

    @Test
    void should_return_full_message_when_park_car_given_car_smallParkingBoy_parkingSpace_0(){
        //given
        PackingLot packingLot = new PackingLot(0);
        AbstractPackingBoy smallPackingBoy = new SmallParkingBoy(packingLot);
        Car car = new Car("1234");

        //when
        BaseException exception =
                Assertions.assertThrows(NotEnoughPositionException.class, () -> smallPackingBoy.parkCar(car));

        //then
        Assertions.assertEquals(PARKING_HAVE_NO_SPACE,exception.getMessage());
    }

    @Test
    void should_return_wrong_message_when_park_car_given_parked_car_and_smallParkingBoy(){
        //given
        AbstractPackingBoy smallPackingBoy = new SmallParkingBoy();
        Car car = new Car("1234");

        //when
        smallPackingBoy.parkCar(car);
        BaseException exception = Assertions.assertThrows(CarHaveBeenParkedException.class, () -> smallPackingBoy.parkCar(car));

        //then
        Assertions.assertEquals(PARKING_CAR_HAVE_BEEN_PARKED,exception.getMessage());
    }

    @Test
    void should_return_wrong_message_when_park_car_given_null_car_and_smallParkingBoy(){
        //given
        AbstractPackingBoy smallPackingBoy = new SmallParkingBoy();
        Car car = null;

        //when
        BaseException exception = Assertions.assertThrows(CarIllegalException.class,() -> smallPackingBoy.parkCar(car));

        //then
        Assertions.assertEquals(CAR_NULL_ERROR_MESSAGE,exception.getMessage());
    }

    @Test
    void should_return_current_ticket_when_fetch_car_given_2car_smallParkingBoy_ticket() {
        //given
        AbstractPackingBoy smallParkingBoy = new SmallParkingBoy();

        List<Car> carList = new ArrayList<>();
        Car car1 = new Car("1234");
        Car car2 = new Car("2345");
        carList.add(car1);
        carList.add(car2);
        smallParkingBoy.parkCar(carList);

        Ticket ticket = Ticket.generateTicket(car1);

        //when
        Car fetchCar = smallParkingBoy.fetchCar(ticket);

        //then
        Assertions.assertEquals(car1, fetchCar);
    }

    @Test
    void should_return_null_when_fetch_car_given_2car_smallParkingBoy_wrong_ticket() {
        //given
        AbstractPackingBoy smallParkingBoy = new SmallParkingBoy();

        List<Car> carList = new ArrayList<>();
        Car car1 = new Car("1234");
        Car car2 = new Car("2345");
        Car carNoPacked = new Car("1235");
        carList.add(car1);
        carList.add(car2);
        smallParkingBoy.parkCar(carList);

        Ticket ticket = Ticket.generateTicket(carNoPacked);

        //when
        BaseException exception = Assertions.assertThrows(TicketException.class,() -> smallParkingBoy.fetchCar(ticket));

        //then
        Assertions.assertEquals(FETCHING_ERROR_TICKET, exception.getMessage());
    }

    @Test
    void should_return_wrong_message_when_fetch_car_given_2car_smallParkingBoy_without_ticket() {
        //given
        AbstractPackingBoy smallParkingBoy = new SmallParkingBoy();
        Ticket ticket = null;

        List<Car> carList = new ArrayList<>();
        carList.add(new Car("1234"));
        carList.add(new Car("2345"));
        smallParkingBoy.parkCar(carList);

        //when
        BaseException exception = Assertions.assertThrows(TicketException.class, () -> smallParkingBoy.fetchCar(ticket));

        //then
        Assertions.assertEquals(FETCHING_HAVE_NO_TICKET, exception.getMessage());
    }

    @Test
    void should_return_unrecognized_message_when_fetch_car_given_2car_smallParkingBoy_ticket_has_been_used() {
        //given
        AbstractPackingBoy smallParkingBoy = new SmallParkingBoy();

        List<Car> carList = new ArrayList<>();
        Car car1 = new Car("1234");
        Car car2 = new Car("2345");
        Car carNoPacked = new Car("1235");
        carList.add(car1);
        carList.add(car2);
        smallParkingBoy.parkCar(carList);

        Ticket ticket = Ticket.generateTicket(car1);

        //when
        smallParkingBoy.fetchCar(ticket);
        BaseException exception =
                Assertions.assertThrows(TicketException.class, () -> smallParkingBoy.fetchCar(ticket));

        //then
        Assertions.assertEquals(FETCHING_ERROR_TICKET, exception.getMessage());
    }

    @Test
    void should_return_true_when_park_car_given_fullParkingLot_unFullParkingLot_smallParkingBoy_car() {
        //given
        List<PackingLot> packingLotList = new ArrayList<>();
        PackingLot fullPackingLot = new PackingLot(1);
        PackingLot unFullPackingLot = new PackingLot(2);
        packingLotList.add(fullPackingLot);
        packingLotList.add(unFullPackingLot);

        AbstractPackingBoy smallParkingBoy = new SmallParkingBoy(packingLotList);
        Car car = new Car("1234");

        //when
        smallParkingBoy.parkCar(new Car("2345"));
        Ticket ticket = smallParkingBoy.parkCar(car);

        //then
        Assertions.assertNotNull(ticket);
    }

    @Test
    void should_return_has_been_packed_message_when_park_car_given_unfullParkingLot_after_carParked_unFullParkingLot_smallParkingBoy_and_2Car() {
        //given
        List<PackingLot> packingLotList = new ArrayList<>();
        PackingLot fullPackingLot = new PackingLot(1);
        PackingLot unFullPackingLot = new PackingLot(2);
        packingLotList.add(fullPackingLot);
        packingLotList.add(unFullPackingLot);

        AbstractPackingBoy smallPackingBoy = new SmallParkingBoy(packingLotList);
        Car car1 = new Car("1234");
        Car car2 = new Car("4567");

        //when
        Ticket ticket1 = smallPackingBoy.parkCar(car1);
        smallPackingBoy.parkCar(car2);
        smallPackingBoy.fetchCar(ticket1);
        BaseException exception =
                Assertions.assertThrows(CarHaveBeenParkedException.class, () -> smallPackingBoy.parkCar(car2));


        //then
        Assertions.assertEquals(PARKING_CAR_HAVE_BEEN_PARKED, exception.getMessage());
    }


    // story5
    @Test
    void should_return_true_when_parkingCar_and_find_in_parkingLot2_given_parkingLot1WithSpace5_and_parkingLot2WithSpace10ParkedCar1_and_superSmallParkingBoy_and_car() {
        //given
        List<PackingLot> packingLotList = new ArrayList<>();
        PackingLot packingLot1 = new PackingLot(10);
        PackingLot packingLot2 = new PackingLot(5);

        packingLot1.parkCar(new Car("4444"));
        packingLotList.add(packingLot1);
        packingLotList.add(packingLot2);

        AbstractPackingBoy superSmallPackingBoy = new SuperSmallParkingBoy(packingLotList);
        Car car = new Car("1234");

        //when
        superSmallPackingBoy.parkCar(car);
        List<PackingLot> packingLotListInPackingBoy = superSmallPackingBoy.getPackingLots();
        boolean isFind = packingLotListInPackingBoy.get(1).isCarHaveBeenParked(car);

        //then
        Assertions.assertTrue(isFind);
    }

    @Test
    void should_return_ticket_when_park_given_car_superSmallParkingBoy() {
        //given
        AbstractPackingBoy superSmallPackingBoy = new SuperSmallParkingBoy();
        Car car = new Car("1234");

        //when
        Ticket ticket = superSmallPackingBoy.parkCar(car);

        //then
        Assertions.assertNotNull(ticket);
    }

    @Test
    void should_return_2_ticket_when_park_given_car_superSmallParkingBoy(){
        //given
        AbstractPackingBoy superSmallPackingBoy = new SuperSmallParkingBoy();
        List<Car> carList = new ArrayList<>();
        carList.add(new Car("1234"));
        carList.add(new Car("2345"));

        //when
        List<Ticket> ticketList = superSmallPackingBoy.parkCar(carList);

        //then
        Assertions.assertEquals(2, ticketList.size());
    }

    @Test
    void should_return_full_message_when_park_car_given_car_superSmallParkingBoy_parkingSpace_0(){
        //given
        PackingLot packingLot = new PackingLot(0);
        AbstractPackingBoy superSmallPackingBoy = new SuperSmallParkingBoy(packingLot);
        Car car = new Car("1234");

        //when
        BaseException exception =
                Assertions.assertThrows(NotEnoughPositionException.class, () -> superSmallPackingBoy.parkCar(car));

        //then
        Assertions.assertEquals(PARKING_HAVE_NO_SPACE,exception.getMessage());
    }

    @Test
    void should_return_wrong_message_when_park_car_given_parked_car_and_superSmallParkingBoy(){
        //given
        AbstractPackingBoy superSmallPackingBoy = new SuperSmallParkingBoy();
        Car car = new Car("1234");

        //when
        superSmallPackingBoy.parkCar(car);
        BaseException exception = Assertions.assertThrows(CarHaveBeenParkedException.class, () -> superSmallPackingBoy.parkCar(car));

        //then
        Assertions.assertEquals(PARKING_CAR_HAVE_BEEN_PARKED,exception.getMessage());
    }

    @Test
    void should_return_wrong_message_when_park_car_given_null_car_and_superSmallParkingBoy(){
        //given
        AbstractPackingBoy superSmallParkingBoy = new SuperSmallParkingBoy();
        Car car = null;

        //when
        BaseException exception = Assertions.assertThrows(CarIllegalException.class,() -> superSmallParkingBoy.parkCar(car));

        //then
        Assertions.assertEquals(CAR_NULL_ERROR_MESSAGE,exception.getMessage());
    }

    @Test
    void should_return_true_when_park_car_given_fullParkingLot_unFullParkingLot_superSmallParkingBoy_car() {
        //given
        List<PackingLot> packingLotList = new ArrayList<>();
        PackingLot fullPackingLot = new PackingLot(1);
        PackingLot unFullPackingLot = new PackingLot(2);
        packingLotList.add(fullPackingLot);
        packingLotList.add(unFullPackingLot);

        AbstractPackingBoy superSmallParkingBoy = new SuperSmallParkingBoy(packingLotList);
        Car car = new Car("1234");

        //when
        superSmallParkingBoy.parkCar(new Car("2345"));
        Ticket ticket = superSmallParkingBoy.parkCar(car);

        //then
        Assertions.assertNotNull(ticket);
    }

    @Test
    void should_return_has_been_packed_message_when_park_car_given_unfullParkingLot_after_carParked_unFullParkingLot_superSmallParkingBoy_and_2Car() {
        //given
        List<PackingLot> packingLotList = new ArrayList<>();
        PackingLot fullPackingLot = new PackingLot(1);
        PackingLot unFullPackingLot = new PackingLot(2);
        packingLotList.add(fullPackingLot);
        packingLotList.add(unFullPackingLot);

        AbstractPackingBoy superSmallPackingBoy = new SuperSmallParkingBoy(packingLotList);
        Car car1 = new Car("1234");
        Car car2 = new Car("4567");

        //when
        Ticket ticket1 = superSmallPackingBoy.parkCar(car1);
        superSmallPackingBoy.parkCar(car2);
        superSmallPackingBoy.fetchCar(ticket1);
        BaseException exception = Assertions.assertThrows(CarHaveBeenParkedException.class,() -> superSmallPackingBoy.parkCar(car2));


        //then
        Assertions.assertEquals(PARKING_CAR_HAVE_BEEN_PARKED,exception.getMessage());
    }

    @Test
    void should_return_true_when_parkingCar_and_find_car_in_parkingLot1_and_car2_in_parkingLot2_given_parkingLot1WithSpace5_and_parkingLot2WithSpace4_and_superSmallParkingBoy_and_2car() {
        //given
        List<PackingLot> packingLotList = new ArrayList<>();
        PackingLot packingLot1 = new PackingLot(5);
        PackingLot packingLot2 = new PackingLot(4);
        packingLotList.add(packingLot1);
        packingLotList.add(packingLot2);

        AbstractPackingBoy superSmallPackingBoy = new SuperSmallParkingBoy(packingLotList);
        List<Car> carList = new ArrayList<>();
        Car car1 = new Car("1234");
        Car car2 = new Car("2345");
        carList.add(car1);
        carList.add(car2);

        //when
        superSmallPackingBoy.parkCar(carList);
        List<PackingLot> packingLotListInPackingBoy = superSmallPackingBoy.getPackingLots();
        boolean isFindCar1InParkingLot1 = packingLotListInPackingBoy.get(0).isCarHaveBeenParked(car1);
        boolean isFindCar2InParkingLot2 = packingLotListInPackingBoy.get(1).isCarHaveBeenParked(car2);

        //then
        Assertions.assertTrue(isFindCar1InParkingLot1 && isFindCar2InParkingLot2);
    }
}
